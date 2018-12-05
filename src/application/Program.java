package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CareRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel ");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Retorno: ");
		Date finish = sdf.parse(sc.nextLine());
			
		CareRental cr = new CareRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Valor por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Valor por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		rentalService.processInvoice(cr);		
		
		System.out.println("Fatura:");
		System.out.println("Valor base: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Taxa: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
	
		sc.close();		
		
	}
}
