/**
* Name: Christian Ishimwe
* Pennkey: ishimwe
* Execution: N/A. this class is used by board.java for its implementation
*
* Description: this class represents method of the snake.
* snake is composed by its head and segments objects
**/
import java.util.ArrayList;

public class Snake {
    private double cellWidth, cellHeight;
    private int headRow, headCol, previousHeadRow, previousHeadCol;
    private int xVel, yVel;
    private String orientation;
    private ArrayList<Segments> manySegments;
    private boolean gameHasStarted;
    private boolean hardModeActive; // keeps track of the hard mode
    private boolean normalModeActive; // keeps track of the normal mode
    
    public Snake(int headRow, int headCol, double cellWidth, double cellHeight) {
        this.headRow = headRow;
        this.headCol = headCol;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        orientation = "";
        manySegments = new ArrayList<Segments>();
        gameHasStarted = false;
        hardModeActive = false;
        normalModeActive = true;
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method updates the position of the snake's head
    * and the segments as the snake moves on the board
    */
    public void update() {
        if (PennDraw.hasNextKeyTyped()) {
            String theKey = "";
            theKey += PennDraw.nextKeyTyped();
            if (theKey.equals("w") && rightOrientationWhileWisTyped()) {
                yVel = 1;
                xVel = 0;
                orientation = "up";
                gameHasStarted = true;
            } else if (theKey.equals("a") && rightOrientationWhileAisTyped()) {
                xVel = -1;
                yVel = 0;
                orientation = "left";
                gameHasStarted = true;
            } else if (theKey.equals("s") && rightOrientationWhileSisTyped()) {
                yVel = -1;
                xVel = 0;
                orientation = "down";
                gameHasStarted = true;
            } else if (theKey.equals("d") && rightOrientationWhileDisTyped()) {
                xVel = 1;
                yVel = 0;
                orientation = "right";
                gameHasStarted = true;
                
                /*check if the player has pressed "m" to help him or her
                * shift between normal and hard mode*/
            } else if (theKey.equals("m") && gameHasStarted) {
                if (normalModeActive == true) {
                    hardModeActive = true;
                    normalModeActive = false;
                } else if (hardModeActive == true) {
                    normalModeActive = true;
                    hardModeActive = true;
                }
            }
        }
        
        previousHeadCol = headCol;
        previousHeadRow = headRow;
        headRow += xVel;
        headCol += yVel;
        
        if (manySegments.size() == 0) {
            boolean noSegmentPresent = true;
            
        } else if (manySegments.size() == 1) {
            manySegments.get(0).updateSegment(previousHeadCol,
            previousHeadRow, orientation);
        } else {
            manySegments.get(0).updateSegment(previousHeadCol, previousHeadRow,
            orientation);
            for (int i = 0; i < manySegments.size() - 1; i++) {
                int precedingCol = manySegments.get(i).getPreviousCol();
                int precedingRow = manySegments.get(i).getPreviousRow();
                
                String precedingOrientation = manySegments.get(i).getOrientation();
                manySegments.get(i + 1).updateSegment(precedingCol,
                precedingRow, precedingOrientation);
            }
            
        }
        
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method draws the snake
    */
    public void draw() {
        PennDraw.setPenColor(0, 0, 0);
        double xPos = 50 + (headRow - 1) * cellWidth + (cellWidth / 2);
        double yPos = 50 + (headCol - 1) * cellHeight + (cellHeight / 2);
        PennDraw.rectangle(xPos, yPos, cellWidth / 2, cellHeight / 2);
        
        for (int i = 0; i < manySegments.size(); i++) {
            manySegments.get(i).draw();
        }
        
    }
    
    /**
    * Inputs: void
    * Outputs: int
    * Description: this is a getter method of the headRow
    */
    public int getHeadRow() {
        return headRow;
    }
    
    /**
    * Inputs: void
    * Outputs: int
    * Description: this is a getter method of the headCol
    */
    public int getHeadCol() {
        return headCol;
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this is a getter method for the orientation of the snake
    */
    public String getOrientation() {
        return orientation;
        
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method checks whether the snake has eatern a foodItem or
    * the bonus food item so that the snake can genereate a new segment on its tail
    */
    public void handleSegments() {
        if (manySegments.size() == 0) {
            if (orientation.equals("up")) {
                int segmentCol = headCol - 1;
                int segmentRow = headRow;
                Segments createdSegment = new Segments(segmentCol, segmentRow,
                cellWidth, cellHeight, "up");
                manySegments.add(createdSegment);
            }
            if (orientation.equals("down")) {
                int segmentCol = headCol + 1;
                int segmentRow = headRow;
                Segments createdSegment = new Segments(segmentCol, segmentRow,
                cellWidth, cellHeight, "down");
                manySegments.add(createdSegment);
            }
            if (orientation.equals("left")) {
                int segmentCol = headCol;
                int segmentRow = headRow + 1;
                Segments createdSegment = new Segments(segmentCol, segmentRow,
                cellWidth, cellHeight, "left");
                manySegments.add(createdSegment);
            }
            if (orientation.equals("right")) {
                int segmentCol = headCol;
                int segmentRow = headRow - 1;
                Segments createdSegment = new Segments(segmentCol, segmentRow,
                cellWidth, cellHeight, "right");
                manySegments.add(createdSegment);
            }
        } else {
            int length = manySegments.size();
            int lastSegmentCol = manySegments.get(length - 1).getCurrentCol();
            int lastSegmentRow = manySegments.get(length - 1).getCurrentRow();
            String lastOrientation = manySegments.get(length - 1).getOrientation();
            
            if (lastOrientation.equals("up")) {
                Segments createdSegment = new Segments(lastSegmentCol - 1,
                lastSegmentRow, cellWidth, cellHeight, "up");
                manySegments.add(createdSegment);
            }
            if (lastOrientation.equals("down")) {
                Segments createdSegment = new Segments(lastSegmentCol + 1,
                lastSegmentRow, cellWidth, cellHeight, "down");
                manySegments.add(createdSegment);
            }
            if (lastOrientation.equals("left")) {
                Segments createdSegment = new Segments(lastSegmentCol,
                lastSegmentRow + 1, cellWidth, cellHeight, "left");
                manySegments.add(createdSegment);
            }
            if (lastOrientation.equals("right")) {
                Segments createdSegment = new Segments(lastSegmentCol,
                lastSegmentRow - 1, cellWidth, cellHeight, "right");
                manySegments.add(createdSegment);
            }
            
        }
        
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this method checks if the snake crashed with the
    * board's perimeter or if the head crashed into one of the snake's
    * segments
    */
    public boolean snakeWentRestricted() {
        double xPos = 50 + (headRow - 1) * cellWidth + (cellWidth / 2);
        double yPos = 50 + (headCol - 1) * cellHeight + (cellHeight / 2);
        double  maxXposRight = 50 + (20 * cellWidth);
        double maxYposUp = 50 + (20 * cellHeight);
        double maxYposDown = 50;
        double maxXposLeft = 50;
        return  xPos > maxXposRight || xPos < 50 || yPos > maxYposUp ||
        yPos < 50 || snakeHitsItself();
        
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: checks if the snake crashed into one of its segments
    */
    private boolean snakeHitsItself() {
        for (int i = 0; i < manySegments.size(); i++) {
            if (headRow == manySegments.get(i).getCurrentRow() &&
            headCol == manySegments.get(i).getCurrentCol()) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this method restricts the snake to move left it
    * was origninally moving right
    */
    private boolean rightOrientationWhileAisTyped() {
        if (orientation.equals("right")) {
            return false;
        }
        return true;
        
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this method restricts the snake to move right it
    * was origninally moving left
    */
    private boolean rightOrientationWhileDisTyped() {
        if (orientation.equals("left")) {
            return false;
        }
        return true;
        
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this method restricts the snake to move up it
    * was origninally moving down
    */
    private boolean rightOrientationWhileSisTyped() {
        if (orientation.equals("up")) {
            return false;
        }
        return true;
        
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this method restricts the snake to move down it
    * was origninally moving up
    */
    private boolean rightOrientationWhileWisTyped() {
        if (orientation.equals("down")) {
            return false;
        }
        return true;
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this is a getter method that return the state of normal mode
    */
    public boolean getNormalMode() {
        return normalModeActive;
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this is a getter method that return the state of normal mode
    */
    public boolean getHardMode() {
        return hardModeActive;
    }
    
    /**
    * Inputs: void
    * Outputs: ArrayList of segments
    * Description: this is a getter method for the list of segments
    */
    public ArrayList<Segments> getSegments() {
        return manySegments;
        
    }
    
}
