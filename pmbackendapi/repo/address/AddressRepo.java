package com.miu.pmtbackendapi.repo.address;

import com.miu.pmtbackendapi.domain.address.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
