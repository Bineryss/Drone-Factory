package SpecificExceptions;

public class DuplicatManagementSystemException extends Exception{

    public DuplicatManagementSystemException() {
        super("Es darf nur jeweils ein Managementsystem geben!");
    }
}
