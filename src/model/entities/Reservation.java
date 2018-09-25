package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer roomNumber;
	private Date chekin;
	private Date chekout;

	public Reservation(Integer roomNumber, Date chekin, Date chekout) {

		this.roomNumber = roomNumber;
		this.chekin = chekin;
		this.chekout = chekout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getChekin() {
		return chekin;
	}

	public Date getChekout() {
		return chekout;
	}

	public long duration() {
		long diff = chekout.getTime() - chekin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

	}

	public String updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkOut.before(now) || checkIn.before(now)) {
			return "Error in reservation: Reservationdates for update must be future dates";
		} else if (!checkOut.after(checkIn)) {
			return "Error in reservation: Check-out date mush be after check-in date";
		} else {
			this.chekin = chekin;
	 		this.chekout = chekout;
			return null;
		}

	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", chekin: " + sdf.format(chekin) + ", chekout: " + sdf.format(chekout) + ", "
				+ duration() + " nights	";
	}

}
