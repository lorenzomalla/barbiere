package it.lorenzo.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import it.lorenzo.app.bean.UserBookingBean;

@Service
public interface BookingInfoRepository extends CrudRepository<UserBookingBean, Integer> {

	@Query("SELECT b from UserBookingBean b where b.startDateBooking >= :from and b.startDateBooking <= :to ")
	List<UserBookingBean> findByStartDateBookingBetween(@Param("from") Date from, @Param("to") Date to);
	

}
