package zany.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import zany.repository.entity.AllTable;

public interface AllTablesRepository extends JpaRepository<AllTable, String>{
    
    @Query("select t.tableName, t.owner from AllTable t")
    List<AllTable> findAllCustom();
}
