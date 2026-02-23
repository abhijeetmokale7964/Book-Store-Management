<<<<<<< HEAD
package com.bookStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.MyBookList;
import com.bookStore.entity.User;
@Repository
public interface MyBookRepository extends JpaRepository<MyBookList, Integer> {
    List<MyBookList> findByUser(User user);
    List<MyBookList>findByBookId(int bookId);
    
}

=======
package com.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.MyBookList;
@Repository
public interface MyBookRepository extends JpaRepository<MyBookList, Integer> {

}
>>>>>>> 14cc1d7e61fc807763dc644a7324efaae564d440
