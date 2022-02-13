package springbootone.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootone.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
