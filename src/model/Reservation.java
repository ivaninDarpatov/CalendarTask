package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Reservation {
	private LocalDate startingDate;
	private LocalDate endDate;
	private float cost;
	
	public Reservation() {}
	public Reservation(LocalDate sDate, LocalDate eDate, float cost) throws Exception {
		if (sDate != null && eDate != null && sDate.isAfter(eDate)) {
			LocalDate temp = sDate;
			sDate = eDate;
			eDate = temp;
		}
		
		this.setStartingDate(sDate);
		this.setEndDate(eDate);
		this.setCost(cost);
	}
	
	public Set<LocalDate> getDatesRange() throws Exception {
		Set<LocalDate> result = new HashSet<LocalDate>();
		
		LocalDate currentDate = this.startingDate;
		
		do {
			result.add(currentDate);
			currentDate = Reservation.getNextDate(currentDate);
		} while (!currentDate.equals(Reservation.getNextDate(this.endDate)));
		
		return result;
	}
	
	public static LocalDate getNextDate(LocalDate currentDate) throws Exception {
		if (currentDate == null) {
			throw new Exception("invalid current date");
		}

		int year = currentDate.getYear();
		int month = currentDate.getMonthValue();
		int day = currentDate.getDayOfMonth();
		
		if (day == 31 ||
			(day == 30 && (month == 2 ||
				month == 4 || month == 6 ||
				month == 9 || month == 11)) ||
			(day == 29 && month == 2) ||
			(day == 28 && month == 2 && year % 4 != 0)) {
			
			day = 1;
			if (month == 12) {
				month = 1;
				year++;
			} else {
				month++;
			}
		} else {
			day++;
		}
		
		StringBuilder builder = new StringBuilder();
		if (year <= 9) {
			builder.append("000");
		} else {
			if (year <= 99) {
				builder.append("00");
			} else {
				if (year <= 999) {
					builder.append("0");
				}
			}
		}
		
		if (year <= 9999) {
			builder.append(Integer.toString(year) + "-");
		} else {
			builder.append("0000-");
		}
		
		if (month <= 9) {
			builder.append("0");
		}
		
		builder.append(Integer.toString(month) + "-");
		
		if (day <= 9) {
			builder.append("0");
		}
		
		builder.append(Integer.toString(day));
		
		LocalDate result = LocalDate.parse(builder.toString());
		return result;
	}
	
	public int isBefore(Reservation other) {
		return (this.startingDate.isBefore(other.startingDate)) ? -1 : 1;
	}
	
	public LocalDate getStartingDate() {
		return this.startingDate;
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}
	
	public float getCost() {
		return this.cost;
	}
	
	private void setStartingDate(LocalDate sDate) throws Exception {
		if (sDate != null) {
			this.startingDate = sDate;
		} else {
			throw new Exception("invalid starting date");
		}
	}
	
	private void setEndDate(LocalDate eDate) throws Exception {
		if (eDate != null) {
			this.endDate = eDate;
		} else {
			throw new Exception("invalid end date");
		}
	}
	
	private void setCost(float cost) throws Exception {
		if (cost >= 0) {
			this.cost = cost;
		} else {
			throw new Exception("invalid cost");
		}
	}
}
