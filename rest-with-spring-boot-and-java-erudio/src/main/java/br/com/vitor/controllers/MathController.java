package br.com.vitor.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitor.converters.NumberConverter;
import br.com.vitor.exceptions.UnsupportedMathOperationException;
import br.com.vitor.math.SimpleMath;

@RestController
public class MathController {
	private final AtomicLong counter = new AtomicLong();
	private SimpleMath math = new SimpleMath();
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Por favor use valores numéricos");
		}
		return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
		
	}
	
	@RequestMapping(value = "/minus/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double minus(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Por favor use valores numéricos");
		}
		return math.minus(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
		
	}
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mult(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Por favor use valores numéricos");
		}
		return math.mult(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
		
	}
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Por favor use valores numéricos");
		}
		return math.div(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
		
	}
	@RequestMapping(value = "/med/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double med(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Por favor use valores numéricos");
		}
		return math.med(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
		
	}
	@RequestMapping(value = "/sqrt/{numberOne}", method = RequestMethod.GET)
	public Double sqrt(@PathVariable(value = "numberOne") String numberOne) throws Exception{
		if (!NumberConverter.isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Por favor use valores numéricos");
		}
		return math.squareRoot(NumberConverter.convertToDouble(numberOne));
		
	}

	

	

}
