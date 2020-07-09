package com.subg.servicedao;

 
import java.util.List;
import java.util.Optional;

import com.subg.model.Address;

/**
 * AddresServiceDao
 */
public interface AddresServiceDao {

    Address addAddress(Address address);
    Address updateAddress(Address address);
    Optional<Address> getAddress(Long addressId);
    List<Address> getUserAddress(Long userId);
    void deleteAddress(Long addressId);
}