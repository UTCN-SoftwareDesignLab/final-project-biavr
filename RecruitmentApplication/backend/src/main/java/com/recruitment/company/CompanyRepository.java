package com.recruitment.company;

import com.recruitment.company.model.Company;
import com.recruitment.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findCompanyByUsersIsLike(User user);
}
