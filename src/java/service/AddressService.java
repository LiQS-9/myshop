package service;

import entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAllByUId(int uid);

    boolean delete(int naid);

    boolean add(String aname, String aphone, String adetail,Integer uid);

    boolean setDefault(int aid);

    boolean update(Address address);
}
