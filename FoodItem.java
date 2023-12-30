/**
* Name: Christian Ishimwe
* Pennkey: ishimwe
* Execution: N/A this class is used by board.java for its implementation
*
* Description: this class represens methods for a food item in the game
**/
public class FoodItem {
    int col, row;
    double cellWidth;
    boolean isHit;
    public FoodItem(int col, int row, double cellWidth) {
        this.col = col;
        this.row = row;
        this.cellWidth = cellWidth;
        isHit = false;
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description:  this method drws the food item
    */
    public void draw() {
        PennDraw.setPenColor(0, 0, 0);
        double radius = cellWidth / 2;
        double xPos = 50 + (row - 1) * cellWidth + (cellWidth / 2);
        double yPos = 50 + (col - 1) * cellWidth + (cellWidth / 2);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledCircle(xPos, yPos, radius);
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.circle(xPos, yPos, radius);
        
    }
    
    /**
    * Inputs: numcols, numRows, snake, and bonus food item
    * Outputs: void
    * Description: this method updates the positions of the food item as soon
    * as the snake eats the previous food item
    */
    public void update(int numCols, int numRows, Snake snake,
    BonusFoodItem bonusFoodItem) {
        if (isHit) {
            boolean emptyCellIsNotFound = true;
            
            boolean checkSegments;
            int newCol = 0;
            int newRow = 0;
            
            // this loop checks for an empty cell where we can place our food item
            while (emptyCellIsNotFound) {
                boolean haveAwrongCell = false;
                
                // generates rondom combinations of food item's row and col
                newCol = (int) (Math.random() * (numCols + 1));
                newRow = (int) (Math.random() * (numRows + 1));
                
                // prevents the food item to be created outside the board's perimeter
                if (newCol == 0 || newRow == 0) {
                    emptyCellIsNotFound = true;
                    haveAwrongCell = true;
                    
                    /* prevents the foood item to be placed in
                    * the position of the snake's head*/
                } else if (newCol == snake.getHeadCol() &&
                newRow == snake.getHeadRow()) {
                    emptyCellIsNotFound = true;
                    haveAwrongCell = true;
                    
                    /*prevents the food item to be placed in the
                    * position of the bonus food item*/
                } else if (newCol == bonusFoodItem.getCol() &&
                newRow == bonusFoodItem.getRow()) {
                    emptyCellIsNotFound = true;
                    haveAwrongCell = true;
                    
                    /* prevents the food item to be placed in the position
                    * that is already occupied by one of the snake's segments
                    */
                } else {
                    for (int i = 0; i < snake.getSegments().size(); i++) {
                        if (newCol == snake.getSegments().get(i).getCurrentCol() &&
                        newRow == snake.getSegments().get(i).getCurrentRow()) {
                            emptyCellIsNotFound = true;
                            haveAwrongCell = true;
                            i = snake.getSegments().size() + 1;
                        }
                        
                    }
                    if (haveAwrongCell) {
                        continue;
                    } else {
                        emptyCellIsNotFound = false;
                    }
                    
                }
                
            }
            
            col = newCol;
            row = newRow;
            isHit = false;
        }
    }
    
    /**
    * Inputs: void
    * Outputs: int
    * Description: this is a getter method for the food Item's row
    */
    public int getRow() {
        return row;
    }
    
    /**
    * Inputs: void
    * Outputs: int
    * Description: this is a getter method for the food Item's column
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
}
