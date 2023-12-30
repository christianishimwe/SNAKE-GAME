/**
* Name: Christian Ishimwe
* Pennkey: ishimwe
* Execution: N/A this class is used by board.java for its implementation
*
* Description: this class represents several methods for the bonus food item
**/
public class BonusFoodItem {
    int col, row;
    double cellWidth;
    boolean isHit; // variable that checks if the bonus food item is hit
    public BonusFoodItem(int col, int row, double cellWidth) {
        this.col = col;
        this.row = row;
        this.cellWidth = cellWidth;
        isHit = false;
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method draws the bonus food item
    */
    public void draw() {
        double radius = cellWidth / 2;
        double xPos = 50 + (row - 1) * cellWidth + (cellWidth / 2);
        double yPos = 50 + (col - 1) * cellWidth + (cellWidth / 2);
        PennDraw.filledCircle(xPos, yPos, radius);
        
    }
    
    /**
    * Inputs: void
    * Outputs: int representation of row
    * Description: this is a getter method fot row
    */
    public int getRow() {
        return row;
    }
    
    /**
    * Inputs: void
    * Outputs: int representation of col
    * Description: this is the getter method for the col
    */
    public int getCol() {
        return col;
    }
    
    /**
    * Inputs: boolean
    * Outputs: void
    * Description: this is a setter method for isHit
    */
    public void setIsHit(boolean value) {
        isHit = value;
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this is a getter method for isHit
    */
    public boolean getIsHit() {
        return isHit;
    }
}
