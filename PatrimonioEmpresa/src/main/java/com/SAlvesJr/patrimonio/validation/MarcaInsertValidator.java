package com.SAlvesJr.patrimonio.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.SAlvesJr.patrimonio.model.dto.MarcaDto;
import com.SAlvesJr.patrimonio.model.entity.Marca;
import com.SAlvesJr.patrimonio.repositories.MarcaRepository;
import com.SAlvesJr.patrimonio.resource.exception.FieldMessage;

public class MarcaInsertValidator implements ConstraintValidator<MarcaInsert, MarcaDto> {

	private MarcaRepository marcaRepository;

	public MarcaInsertValidator(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}

	@Override
	public void initialize(MarcaInsert ann) {
	}

	@Override
	public boolean isValid(MarcaDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Marca aux = marcaRepository.findByNome(objDto.getNome());
		if (aux != null) {
			list.add(new FieldMessage("nome", "Nome de Marca já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getFildMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}