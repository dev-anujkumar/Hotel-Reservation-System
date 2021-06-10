package com.hotelreservation;

import com.hotelreservation.controller.Output;
import com.hotelreservation.entity.CustomerType;
import com.hotelreservation.entity.Hotels;
import com.hotelreservation.services.HotelOperations;
import com.hotelreservation.services.IHotelOperations;
import com.hotelreservation.services.Rate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;

public class HotelReservationTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private HotelOperations hotelOperations;
    private Hotels lakewood;
    private Hotels bridgewood;
    private Hotels ridgewood;

    @Before
    public void setUp() throws Exception {
        hotelOperations = new HotelOperations();

        HashMap<CustomerType, Rate> customerTypeRateHashMap = new HashMap<>();
        customerTypeRateHashMap.put(CustomerType.REGULAR,new Rate(110,90));
        customerTypeRateHashMap.put(CustomerType.REWARD, new Rate(80,80));
        lakewood = new Hotels("Lakewood", 3,customerTypeRateHashMap);

        customerTypeRateHashMap = new HashMap<>();
        customerTypeRateHashMap.put(CustomerType.REGULAR,new Rate(160,40));
        customerTypeRateHashMap.put(CustomerType.REWARD, new Rate(110,50));
        bridgewood = new Hotels("Bridgewood", 4,customerTypeRateHashMap);

        customerTypeRateHashMap = new HashMap<>();
        customerTypeRateHashMap.put(CustomerType.REGULAR,new Rate(220,150));
        customerTypeRateHashMap.put(CustomerType.REWARD, new Rate(100,40));
        ridgewood = new Hotels("Ridgewood", 5,customerTypeRateHashMap);

        hotelOperations.addHotels(lakewood);
        hotelOperations.addHotels(bridgewood);
        hotelOperations.addHotels(ridgewood);

    }

    @Test
    public void givenHotelNameAndRate_WhenAddedToList_ShouldReturnNumberOfHotels() {
        Assert.assertEquals(3, hotelOperations.getNumberOfHotels());
    }

    @Test
    public void givenHotelsWithRate_WhenCheckedForCheapestHotel_ReturnCheapestRatedHotel() {
        List<Output> cheapestHotel = hotelOperations.findCheapHotel(CustomerType.REGULAR,
                "10Sep2020", "11Sep2020");
        Assert.assertEquals(2,cheapestHotel.size());
        Assert.assertEquals(200,cheapestHotel.get(0).getTotalRate());
    }
}
