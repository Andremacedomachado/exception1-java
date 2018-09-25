package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer roomNumber;
	private Date chekin;
	private Date chekout;

	public Reservation(Integer roomNumber, Date chekin, Date chekout) throws DomainException {
		if (!chekout.after(chekin)) {
			throw new DomainException("Error in reservation: Reservationdates for update must be future dates");
		}
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

	public void updateDates(Date checkIn, Date checkOut) throws DomainException{
		Date now = new Date();
		if (checkOut.before(now) || checkIn.before(now)) {
			throw new DomainException("Error in reservation: Reservationdates for update must be future dates");
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Error in reservation: Check-out date mush be after check-in date");
		}
		this.chekin = checkIn;
		this.chekout = checkOut;

	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", chekin: " + sdf.format(chekin) + ", chekout: " + sdf.format(chekout) + ", "
				+ duration() + " nights	";
	}

}
