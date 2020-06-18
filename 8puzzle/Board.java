
// import java.io.File;
// import java.io.FileNotFoundException;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;



public class Board {


    private final int[][] allTiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException("Constructor expects a 2D array");

        this.allTiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            this.allTiles[i] = new int[tiles[i].length];
            for (int j = 0; j < tiles[i].length; j++) {
                this.allTiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        String strToBeReturned = "";
        for (int i = 0; i < this.allTiles.length; i++) {
            for (int j = 0; j < this.allTiles[i].length; j++) {
                strToBeReturned = strToBeReturned.concat(Integer.toString(this.allTiles[i][j]) + " ");
            }
            strToBeReturned = strToBeReturned.concat("\n");
        }
        return this.dimension() + "\n" + strToBeReturned;
    }

    // board dimension n
    public int dimension() {
        return this.allTiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < this.allTiles.length; i++) {
            for (int j = 0; j < this.allTiles[i].length; j++) {
                // if (this.allTiles[i][j] != (i * this.allTiles[i - 1].length) + (j + 1))
                // if ((i == (this.allTiles.length - 1)) && (j == (this.allTiles[this.allTiles.length - 1].length - 1)))
                if (this.allTiles[i][j] == 0) continue;
                else if (this.allTiles[i][j] != (i * this.allTiles[0].length) + (j + 1)) count += 1;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manh = 0;
        for (int i = 0; i < this.allTiles.length; i++) {
            for (int j = 0; j < this.allTiles[i].length; j++) {
                if (this.allTiles[i][j] == 0) continue;
                else {
                    int element = this.allTiles[i][j];
                    int goalI = (element - 1) / this.allTiles.length;
                    // int goalJ = (element % this.allTiles.length) - 1;
                    int goalJ;
                    if ((element % this.allTiles.length) == 0) goalJ = this.allTiles.length - 1;
                    else goalJ = (element % this.allTiles.length) - 1;

                    manh += Math.abs(goalI - i) + Math.abs(goalJ - j);
                }
            }
        }
        return manh;
    }

    // is this board the goal board?
    public boolean isGoal() {
        boolean toBeReturned = true;
        for (int i = 0; i < this.allTiles.length; i++) {
            for (int j = 0; j < this.allTiles[i].length; j++) {
                if (this.allTiles[i][j] == 0) continue;
                else if (this.allTiles[i][j] != (i * this.allTiles[0].length) + (j + 1)) toBeReturned = false;
            }
        }
        return toBeReturned;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || y.getClass() != this.getClass()) return false;

        Board board = (Board) y;

        if (this.allTiles.length != board.allTiles.length) return false;

        /*
        boolean toBeReturned = true;
        outer: for (int i = 0; i < this.allTiles.length; i++) {
            for (int j = 0; j < this.allTiles[i].length; j++) {
                if (this.allTiles[i][j] != board.allTiles[i][j]) {
                    toBeReturned = false;
                    break outer;
                }
            }
        }

         */
        return Arrays.deepEquals(this.allTiles, board.allTiles);
    }

    private void exchange(int[][] array, int oi, int oj, int ni, int nj) {
        int c = array[oi][oj];
        array[oi][oj] = array[ni][nj];
        array[ni][nj] = c;
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> tempBoardList = new Stack<>();

        outer: for (int i = 0; i < this.allTiles.length; i++) {
            for (int j = 0; j < this.allTiles[i].length; j++) {
                if (this.allTiles[i][j] == 0) {
                    // Checking upper tile
                    if ((i - 1) > - 1) {
                        int[][] tempTiles = new int[this.allTiles.length][this.allTiles.length];
                        for (int ti = 0; ti < this.allTiles.length; ti++) {
                            for (int tj = 0; tj < this.allTiles.length; tj++) {
                                tempTiles[ti][tj] = this.allTiles[ti][tj];
                            }
                        }
                        exchange(tempTiles, i, j, i - 1, j);
                        tempBoardList.push(new Board(tempTiles));
                    }
                    // Checking lower tile
                    // if ((i - 1) > - 1 && (i - 1) < this.allTiles.length) {
                    if ((i + 1) < this.allTiles.length) {
                        int[][] tempTiles = new int[this.allTiles.length][this.allTiles.length];
                        for (int ti = 0; ti < this.allTiles.length; ti++) {
                            for (int tj = 0; tj < this.allTiles.length; tj++) {
                                tempTiles[ti][tj] = this.allTiles[ti][tj];
                            }
                        }
                        exchange(tempTiles, i, j, i + 1, j);
                        tempBoardList.push(new Board(tempTiles));
                    }
                    // Checking left Tile
                    if ((j - 1) > - 1) {
                        int[][] tempTiles = new int[this.allTiles.length][this.allTiles.length];
                        for (int ti = 0; ti < this.allTiles.length; ti++) {
                            for (int tj = 0; tj < this.allTiles.length; tj++) {
                                tempTiles[ti][tj] = this.allTiles[ti][tj];
                            }
                        }
                        exchange(tempTiles, i, j, i, j - 1);
                        tempBoardList.push(new Board(tempTiles));
                    }
                    // Checking right Tile
                    if ((j + 1) < this.allTiles.length) {
                        int[][] tempTiles = new int[this.allTiles.length][this.allTiles.length];
                        for (int ti = 0; ti < this.allTiles.length; ti++) {
                            for (int tj = 0; tj < this.allTiles.length; tj++) {
                                tempTiles[ti][tj] = this.allTiles[ti][tj];
                            }
                        }
                        exchange(tempTiles, i, j, i, j + 1);
                        tempBoardList.push(new Board(tempTiles));
                    }
                    break outer;
                }
            }
        }
        // System.out.println("Size of boardlist: " + tempBoardList.size());
        return tempBoardList;
    }


    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        int[][] tempTiles = new int[this.allTiles.length][this.allTiles.length];

        int gotZeroContainingRow = 0;

        for (int ti = 0; ti < this.allTiles.length; ti++) {
            for (int tj = 0; tj < this.allTiles.length; tj++) {
                tempTiles[ti][tj] = this.allTiles[ti][tj];
                if (this.allTiles[ti][tj] == 0) gotZeroContainingRow = ti;
            }
        }

        for (int i = 0; i < this.allTiles.length; i++) {
            if (i != gotZeroContainingRow) {
                exchange(tempTiles, i, 0, i, 1);
                break;
            }
        }

        return new Board(tempTiles);
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        /*

        try {
            File theFile = new File("D:\\Courses\\algorithms part 1\\Priority Quequ\\PriorityQueue4\\src\\input.txt");
            Scanner pointer = new Scanner(theFile);
            String data;

            int[][] tiles = new int[3][3];
            int[][] newTiles = new int[3][3];



            for (int k = 0; k < 6; k++) {
                data = pointer.nextLine();
                String[] dataSplit = data.split(",");




                for (int l = 0; l < 3; l++) {
                    int input[] = new int[3];
                    input[0] = Integer.parseInt(dataSplit[0]);
                    input[1] = Integer.parseInt(dataSplit[1]);
                    input[2] = Integer.parseInt(dataSplit[2]);

                    if (k < 3) tiles[k][l] = input[l];

                    else newTiles[k - 3][l] = input[l];

                }

            }

            // for (int[] row : tiles) System.out.println(Arrays.toString(row));

            Board a = new Board(tiles);
            Board b = new Board(newTiles);

            // test 1111111111111111111111111

            System.out.println(a.toString());
            System.out.println(a.dimension());
            System.out.println(a.hamming());
            System.out.println(a.manhattan());
            System.out.println(a.isGoal());

            Iterable<Board> b = a.neighbors();

            for (Board g : b) {
                System.out.println(g.toString());
            }

            Board t = a.twin();

            Board ct = new Board(tiles);

            System.out.println(t.toString());

            System.out.println("Check Equals: " + a.equals(t));



            // test 1111111111111111111111111

            test 22222222222222222222222


            int[] allTiles = new int[(3 * 3) - 1];
            int allTilesIndex = 0;

            String workWith = a.toString();
            String[] lineSplit = workWith.split("\n");
            for (int i = 0; i < lineSplit.length; i++) {
                String[] eachStr = lineSplit[i].split(" ");
                for (int j = 0; j < eachStr.length; j++) {
                    System.out.println(eachStr[j]);
                    System.out.println(i + " " + j);
                    // allTiles[allTilesIndex] = Integer.parseInt(eachStr[j].replaceAll("\\s", ""));
                    int theInt = Integer.parseInt(eachStr[j]);
                    if (theInt == 0) continue;
                    allTiles[allTilesIndex] = theInt;
                    allTilesIndex++;
                }
            }
            System.out.println("Gap Gap");

            System.out.println(a.toString());

            System.out.println(Arrays.toString(allTiles));

            int abcd = 0 % 2;
            System.out.println("ABCD: " + abcd);

            test 22222222222222222222222222222


            System.out.println(a.toString());
            System.out.println(b.toString());

            System.out.println("Check Equals: " + a.equals(b));


        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

         */

    }



}