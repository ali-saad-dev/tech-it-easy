package nl.novi.techiteasy.repository;


import nl.novi.techiteasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    public List<Television> findTelevisionByName(String name);

}
