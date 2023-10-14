package org.anagramly.repository;

import org.anagramly.model.AnagramResult;
import org.springframework.data.repository.CrudRepository;

public interface AnagramRepository extends CrudRepository<AnagramResult, Long> {
}
