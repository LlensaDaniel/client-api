package com.client.api.service;


import com.client.api.dto.RequestDto;
import com.client.api.*;

import com.client.api.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ClientService {

	private final String ENTER = "\n";

	private final List<Client> clients = new ArrayList<>();

	public ResponseEntity createClient(RequestDto clientDto) {
		String message = "";
		HttpStatus status = HttpStatus.OK;
		if(isValidClient(clientDto)) {
			Client client = new Client(clientDto.getNombre(), clientDto.getApellido(), clientDto.getFechaNacimiento(), clientDto.getEdad());
			clients.add(client);
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "La información del cliente ingresado no es válida. Por favor verificar y reintentar.";
		}
		return new ResponseEntity(message, status);
	}


	public ResponseEntity getKpiClients() {
		return new ResponseEntity(generateKpiClients(), HttpStatus.OK);
	}

	private String generateKpiClients(){
		String kpisGenerated = "";

		List<Integer> clientsAges = clients.stream()
				.map(c -> c.getAge().intValue())
				.collect(Collectors.toList());

		double average = getAverage(clientsAges);
		double standardDiviation = getStandardDiviation(convertListToPrimitiveArray(clientsAges));

		kpisGenerated = "El promedio de edad de los clientes es: " + average + "." + ENTER;
		kpisGenerated += "La desviación estandar de edad de clientes es: " + standardDiviation + ".";

		return kpisGenerated;
	}

	public ResponseEntity getListClient() {
		return new ResponseEntity(generateReportClients(), HttpStatus.OK);
	}

	private String generateReportClients() {
		String report = "Reporte Clientes" +ENTER+ENTER;

		for (Client c : clients) {
			report += "Nombre: " + c.getName() + ENTER;
			report += "Apellido: " + c.getLastName() + ENTER;
			report += "Fecha de nacimiento: " + c.getBirthDateAsString() + ENTER;
			report += "Edad: " + c.getAge() + ENTER;
			report += "Fecha Probable de muerte: " + calculateProbabilyDateDeath(c.getBirthDateAsString());
			report += "--------------------------" + ENTER;
		}
		return report;
	}

	private int calculateProbabilyDateDeath(String BirthDateAsString) {

		// Fuente: Numerología.

		String[] dateSplited = BirthDateAsString.split("/");

		int day = Integer.valueOf(dateSplited[0]);
		int month = Integer.valueOf(dateSplited[1]);
		int year = Integer.valueOf(dateSplited[2]);

		int sum = day + month + year;

		return sum + month + (month*2);
	}

	private double getAverage(List<Integer> clientsAges) {
		return clientsAges.stream().mapToInt(Integer::intValue).average().getAsDouble();
	}

	private double getVariance(double[] x) {
		int m = x.length;
		double sum = 0;
		// suma
		for (int i = 0; i < m; i++) {
			sum += x[i];
		}

		double dAve = sum / m;
		double dVar = 0;

		for (int i = 0; i < m; i++) {
			dVar += (x[i] - dAve) * (x[i] - dAve);
		}
		return dVar / m;
	}

	// desviación estándar σ = sqrt (s ^ 2)
	private double getStandardDiviation(double[] x) {
		return Math.sqrt(getVariance(x));
	}

	public double[] convertListToPrimitiveArray(List<Integer> integers)
	{
		double[] ret = new double[integers.size()];
		Iterator<Integer> iterator = integers.iterator();
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = iterator.next().doubleValue();
		}
		return ret;
	}

	private boolean isValidClient(RequestDto clientDto) {
		return isNoNullAndNoBlack(clientDto.getNombre()) &&
				isNoNullAndNoBlack(clientDto.getApellido()) &&
				isValidAge(clientDto.getEdad()) &&
				isValidDate(clientDto.getFechaNacimiento());
	}

	private boolean isValidAge(Integer age) {
		return age != null && age.intValue() > 0;
	}

	private boolean isNoNullAndNoBlack(String data) {
		return data != null && !data.isBlank();
	}

	private boolean isValidDate (String date) {
		boolean isValid = isNoNullAndNoBlack(date);
		if(isValid) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			try {
				dateFormat.parse(date);
			}
			catch (ParseException e) {
				isValid = false;
			}
		}
		return isValid;
	}

}
