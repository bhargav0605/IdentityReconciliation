/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.service;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.dto.IdentityResponse;

public interface IdentityService {
	
	IdentityResponse identify(IdentityRequest identityRequest);

}
