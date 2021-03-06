package com.parkit.parkingsystem.service;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @atuthor Tek and Subhi
 * Allows to choose the type of parkingService(processIncoming or processExisting) or to exit the parking system
 */
public class InteractiveShell {
    private static final Logger logger = LogManager.getLogger("InteractiveShell");
    private InputReaderUtil inputReaderUtil ;
    private  ParkingSpotDAO parkingSpotDAO ;
    private  TicketDAO ticketDAO ;
    private  ParkingService parkingService ;
    public void setInputReaderUtil(InputReaderUtil inputReaderUtil) {
        this.inputReaderUtil = inputReaderUtil;
    }
    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    public InteractiveShell(){
        this.inputReaderUtil =new InputReaderUtil();
        this.parkingSpotDAO = new ParkingSpotDAO();
        this.ticketDAO= new TicketDAO();
        this.parkingService= new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    }

    /**
     * Provide the information of the parking system for the interaction between the users and the system
     */
    public void loadInterface() {
        logger.info("App initialized!!!");
        System.out.println("Welcome to Parking System!");

        boolean continueApp = true;

        while(continueApp){
            loadMenu();
            int option = inputReaderUtil.readSelection();
            switch(option){
                case 1: {
                    parkingService.processIncomingVehicle();
                    break;
                }
                case 2: {
                    parkingService.processExitingVehicle();
                    break;
                }
                case 3: {
                    System.out.println("Exiting from the system!");
                    continueApp = false;
                    break;
                }
                default: System.out.println("Unsupported option. Please enter a number corresponding to the provided menu");
            }
        }
    }
    //Showing the information of the parking system
    private static void loadMenu(){
        System.out.println("Please select an option. Simply enter the number to choose an action");
        System.out.println("1 New Vehicle Entering - Allocate Parking Space");
        System.out.println("2 Vehicle Exiting - Generate Ticket Price");
        System.out.println("3 Shutdown System");
    }


}
