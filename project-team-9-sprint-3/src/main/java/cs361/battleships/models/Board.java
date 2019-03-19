package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Board {

	@JsonProperty private List<Ship> ships;
	@JsonProperty private List<Result> attacks;
	public List<Square> sonarOccupiedSquares;
	public List<Square> sonarUnoccupiedSquares;
	public List<Square> sonarMiddleSquare;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		ships = new ArrayList<>();
		attacks = new ArrayList<>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		if (ships.size() >= 4) {
			return false;
		}
		if (ships.stream().anyMatch(s -> s.getKind().equals(ship.getKind()))) {
			return false;
		}
		final var placedShip = new Ship(ship.getKind());
		placedShip.place(y, x, isVertical);
		if (ships.stream().anyMatch(s -> s.overlaps(placedShip))) {
			return false;
		}
		if (placedShip.getOccupiedSquares().stream().anyMatch(s -> s.isOutOfBounds())) {
			return false;
		}
		ships.add(placedShip);
		return true;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		Result attackResult = attack(new Square(x, y));
		if(attackResult.getResult() == AttackStatus.HIDDEN) {
		    //don't add attackResult
            return attackResult;
        }
		attacks.add(attackResult);
		return attackResult;
	}

	private Result attack(Square s) {
		if (attacks.stream().anyMatch(r -> r.getLocation().equals(s))) {
			var attackResult = new Result(s);
			attackResult.setResult(AttackStatus.INVALID);
			return attackResult;
		}
		var shipsAtLocation = ships.stream().filter(ship -> ship.isAtLocation(s)).collect(Collectors.toList());
		if (shipsAtLocation.size() == 0) {
			var attackResult = new Result(s);
			return attackResult;
		}
		var hitShip = shipsAtLocation.get(0);
		var attackResult = hitShip.attack(s.getRow(), s.getColumn());
		if (attackResult.getResult() == AttackStatus.SUNK) {
			if (ships.stream().allMatch(ship -> ship.isSunk())) {
				attackResult.setResult(AttackStatus.SURRENDER);
			}
		}
		return attackResult;
	}

	public boolean sonarPulse(int x, char y){
		sonarOccupiedSquares = new ArrayList<>();
		sonarUnoccupiedSquares = new ArrayList<>();
		sonarMiddleSquare = new ArrayList<>();
		sonarMiddleSquare.add(new Square(x, y));


		int row;
		int col;

		for(int j = -1; j < 2; j++) {
			row = x+j;

			for(int i = -1; i < 2; i++) {
				col = (int)y +i;

                checkSonar(row, col);
			}
		}

		for(int i = -2; i < 5; i=i+4) {
			row = x;
			col = (int)y + i;

            checkSonar(row, col);

			row = x + i;
			col = y;

            checkSonar(row, col);
		}

		return true;
	}

	public void checkSonar(int row, int col) {

        if(sonarIsInBounds(row, (char)col)) {

            boolean isOccupied = sonarIsOccupied(row, (char) col);

            if (isOccupied) {
                var s = new Square(row, (char)col);
                sonarOccupiedSquares.add(s);
            } else {
                var s = new Square(row, (char)col);
                sonarUnoccupiedSquares.add(s);
            }
        }
    }

	public boolean sonarIsInBounds(int x, char y){
		var square = new Square(x, y);
		if(square.isOutOfBounds()){
			return false;
		}
		else
			return true;
	}

	public boolean sonarIsOccupied(int x, char y){
		var square = new Square(x, y);
		//next lines taken from attack() function - not sure what they do
		var shipsAtLocation = ships.stream().filter(ship -> ship.isAtLocation(square)).collect(Collectors.toList());
		if (shipsAtLocation.size() == 0) {
			return false; //square is not occupied
		}
		else
			return true; //square is occupied
	}

	List<Ship> getShips() {
		return ships;
	}
}
