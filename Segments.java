/**
* Name: Christian Ishimwe
* Pennkey: ishimwe
* Execution: N/A this class is used by board.java and snake.java for
* their implementation
*
* Description: this classes represents methods for all segments of the
* snake except the snake's head
**/

public class Segments {
    private double cellWidth, cellHeight;
    private int currentRow, currentCol;
    private int previousRow, previousCol;
    private String orientation; //keeps track of the direcion of the segment
    
    private int xVel, yVel;
    
    public Segments(int currentCol, int currentRow, double cellWidth,
    double cellHeight, String orientation) {
        this.currentRow = currentRow;
        this.currentCol = currentCol;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.orientation = orientation;
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method draws each segments
    */
    public void draw() {
        PennDraw.setPenColor(0, 0, 0);
        double xPos = 50 + (currentRow - 1) * cellWidth + (cellWidth / 2);
        double yPos = 50 + (currentCol - 1) * cellHeight + (cellHeight / 2);
        PennDraw.setPenColor(160, 32, 240);
        PennDraw.filledRectangle(xPos, yPos, cellWidth / 2, cellHeight / 2);
    }
    
    /**
    * Inputs: int updatedCol, int updatedRow, and the String Orieantation
    * Outputs: void
    * Description: this method is used to update the position of the
    * snake's first segment as the snake moves
    */
    public void updateSegment(int updatedCol, int updatedRow,
    String orientation) {
        previousCol = currentCol;
        previousRow = currentRow;
        currentRow = updatedRow;
        currentCol = updatedCol;
        this.orientation = orientation;
        
    }
    
    /**
    * Inputs: void
    * Outputs: int representation of the previousCol
    * Description: this is a getter method for the previousCol
    */
    public int getPreviousCol() {
        return previousCol;
    }
    
    /**
    * Inputs: void
    * Outputs: int representation of the previousRow
    * Description: this is a getter method for the previousCol
    */
    public int getPreviousRow() {
        return previousRow;
    }
    
    /**
    * Inputs:
    * Outputs: int representation of the currentCol
    * Description: this is a getter method for the CurrentCol
    */
    public int getCurrentCol() {
        return currentCol;
    }
    
    /**
    * Inputs: void
    * Outputs: integer representation of the currentRow
    * Description: this is a getter method for the currentRow
    */
    public int getCurrentRow() {
        return currentRow;
    }
    
    /**
    * Inputs: void
    * Outputs: String representation of the orientation
    * Description: TODO
    */
    public String getOrientation() {
        return orientation;
    }
    
}
