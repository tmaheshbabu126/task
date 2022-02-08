package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.SearchKeyWords;







@Repository
public interface SearchKeyWordsRepository extends JpaRepository<SearchKeyWords,Long> {

	SearchKeyWords findByKeyword(String q);

	
		
		
}
