package com.subg.servicedaoImpl;

 
import java.util.List;
import java.util.Optional;

import com.subg.dao.AddressDaoRepository;
import com.subg.model.Address;
import com.subg.servicedao.AddresServiceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AddresServiceDaoImpl
 */
@Service
@Transactional
public class AddresServiceDaoImpl implements AddresServiceDao {

    @Autowired
    private AddressDaoRepository addressDaoRepository;

    @Override
    public Address addAddress(Address address) { 
        return addressDaoRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return null;
    }

    @Override
    public Optional<Address> getAddress(Long addressId) {
        return addressDaoRepository.findById(addressId);
    }

    @Override
    public List<Address> getUserAddress(Long userId) { 
        return addressDaoRepository.getUserAddress(userId);
    }

    @Override
    public void deleteAddress(Long addressId) {
        // TODO Auto-generated method stub

    }

    
}