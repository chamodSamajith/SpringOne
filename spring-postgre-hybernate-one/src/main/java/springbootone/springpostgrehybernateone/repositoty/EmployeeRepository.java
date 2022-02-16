package springbootone.springpostgrehybernateone.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootone.springpostgrehybernateone.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
