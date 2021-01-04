package org.launchcode.recipeapp.models.data;

import org.launchcode.recipeapp.models.Instruction;
import org.springframework.data.repository.CrudRepository;

public interface InstructionRepository extends CrudRepository<Instruction, Integer>  {

}
