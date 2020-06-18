import edu.princeton.cs.algs4.MinPQ;
// import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
// import edu.princeton.cs.algs4.StdOut;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.Scanner;



public class Solver {
    private Stack<Board> allMoves;
    private MinPQ<SearchNode> pq1;
    private MinPQ<SearchNode> pq2;
    private final int dimension;
    private final Board root;
    private final boolean isSolvable;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board thisClassCurrentBoard;
        private final int thisClassNumberOfMoves;
        private final SearchNode thisClassPreviousSearchNode;
        private final int priority;
        private final int manhattan;

        private SearchNode (Board current, int numOfMoves, SearchNode previous) {
            this.thisClassCurrentBoard = current;
            this.thisClassNumberOfMoves = numOfMoves;
            this.thisClassPreviousSearchNode = previous;
            this.manhattan = current.manhattan();
            this.priority = current.manhattan() + numOfMoves;
        }

        @Override
        public int compareTo(SearchNode o) {
            if (this.priority < o.priority) return -1;
            if (this.priority > o.priority) return 1;
            if (this.manhattan < o.manhattan) return -1;
            if (this.manhattan > o.manhattan) return 1;

            return 0;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Constructor expects a 2D array");
        this.allMoves = new Stack<Board>();
        this.dimension = initial.dimension();
        this.root = initial;

        this.pq1 = new MinPQ<SearchNode>();
        this.pq2 = new MinPQ<SearchNode>();


        if (this.root.isGoal()) { this.isSolvable = true; }

        else {
            SearchNode a = new SearchNode(initial, 0, null);
            SearchNode b = new SearchNode(this.root.twin(), 0, null);

            // this.pq1.insert(a);
            // this.pq2.insert(b);


            while (true) {

                if (a.thisClassCurrentBoard.isGoal()) {
                    this.isSolvable = true;

                    SearchNode prev = a;
                    while (prev != null) {
                        this.allMoves.push(prev.thisClassCurrentBoard);
                        prev = prev.thisClassPreviousSearchNode;
                    }
                    this.allMoves.push(initial);

                    break;
                }
                if (b.thisClassCurrentBoard.isGoal()) {
                    this.isSolvable = false;
                    break;
                }


                for (Board eachNeighbor : a.thisClassCurrentBoard.neighbors()) {
                    boolean canInsert = true;

                    if (a.thisClassPreviousSearchNode == null) canInsert = true;

                    else if (eachNeighbor.equals(a.thisClassPreviousSearchNode.thisClassCurrentBoard)) {
                        canInsert = false;
                    }

                    if (canInsert) {
                        this.pq1.insert(new SearchNode(eachNeighbor, a.thisClassNumberOfMoves + 1, a));
                    }
                }

                for (Board eachNeighbor : b.thisClassCurrentBoard.neighbors()) {
                    boolean canInsert = true;

                    if (b.thisClassPreviousSearchNode == null) canInsert = true;

                    else if (eachNeighbor.equals(b.thisClassPreviousSearchNode.thisClassCurrentBoard)) {
                        canInsert = false;
                    }

                    if (canInsert) {
                        this.pq2.insert(new SearchNode(eachNeighbor, b.thisClassNumberOfMoves + 1, b));
                    }
                }

                a = this.pq1.delMin();
                b = this.pq2.delMin();
            }
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.isSolvable;

    }

    // min number of moves to solve initial board
    public int moves() {
        if (this.root.isGoal()) return 0;
        else if (this.isSolvable) return this.allMoves.size() - 2;
        else return  - 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (this.isSolvable) return allMoves;
        else return null;
    }


    // test client (see below)
    public static void main(String[] args) {

        /*
        try {
            File theFile = new File("D:\\Courses\\algorithms part 1\\Priority Quequ\\PriorityQueue4\\src\\input.txt");
            Scanner pointer = new Scanner(theFile);
            String data;

            int[][] tiles = new int[3][3];



            for (int k = 0; k < 3; k++) {
                data = pointer.nextLine();
                String[] dataSplit = data.split(",");
                int input[] = new int[3];
                input[0] = Integer.parseInt(dataSplit[0]);
                input[1] = Integer.parseInt(dataSplit[1]);
                input[2] = Integer.parseInt(dataSplit[2]);

                for (int l = 0; l < 3; l++) {

                    tiles[k][l] = input[l];
                }
            }

            Board initial = new Board(tiles);

            Solver solver = new Solver(initial);

            if (!solver.isSolvable())
                StdOut.println("No Solution Possible.");
            else {
                StdOut.println("Minimum number of moves = " + solver.moves());
                for (Board board : solver.solution())
                    StdOut.println(board);
            }


        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

         */
    }

}
