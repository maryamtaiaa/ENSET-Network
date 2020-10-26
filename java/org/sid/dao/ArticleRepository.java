package org.sid.dao;

import org.sid.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ArticleRepository extends JpaRepository<Article, Long>{
	

}
