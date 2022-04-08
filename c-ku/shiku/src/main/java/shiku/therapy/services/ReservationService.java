package shiku.therapy.services;

import java.util.Collection;

import shiku.therapy.entity.Reservation;
import shiku.therapy.temp.CurrentReservation;

import javax.validation.Valid;

//Service Pattern for Reservation
public interface ReservationService {
	
	public Reservation getReservationForLoggedUserById(int resId);

	public Collection<Reservation> getReservationsForLoggedUser();
	
	public void saveOrUpdateReservation(@Valid CurrentReservation currentReservation);
	
	public void deleteReservation(int resId);

	public CurrentReservation reservationToCurrentReservationById(int resId);

}
