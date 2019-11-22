import java.util.ArrayList;

public class Board {

    private int[][] boardTiles;
    private int rows;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        boardTiles = tiles;
        rows = boardTiles.length;
    }

    // string representation of this board
    public String toString() {
        String str = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                str = str + " " + boardTiles[i][j];
            }
        }
        return str.substring(1, str.length());
    }

    // board dimension n
    public int dimension() {
        return rows;
    }

    // number of tiles out of place
    public int hamming() {
        int dist = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (boardTiles[i][j] != 0) {
                    if (boardTiles[i][j] != ((i * rows) + j + 1)) {
                        dist++;
                    }
                }
            }
        }
        return dist;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int dist = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (boardTiles[i][j] != 0) {
                    if (boardTiles[i][j] != ((i * rows) + j + 1)) {
                        x = ((boardTiles[i][j] - 1) / rows);
                        y = ((boardTiles[i][j] - 1) % rows);
                        dist = dist + Math.abs(x - i) + Math.abs(y - j);
                    }
                }
            }
        }
        return dist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return toString().equals(y.toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> iter = new ArrayList<Board>();
        int i = 0;
        int j = 0;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < rows; j++) {
                if (boardTiles[i][j] == 0) {
                    break;
                }
            }
        }
        int x = i;
        int y = j;

        if (x - 1 >= 0) {
            int[][] temp = new int[rows][rows];
            for (i = 0; i < rows; i++) {
                for (j = 0; j < rows; j++) {
                    temp[i][j] = boardTiles[i][j];
                }
            }
            int tempint = temp[x][y];
            temp[x][y] = temp[x - 1][y];
            temp[x - 1][y] = tempint;
            Board b = new Board(temp);
            iter.add(b);
        }
        if (x + 1 < rows) {
            int[][] temp = new int[rows][rows];
            for (i = 0; i < rows; i++) {
                for (j = 0; j < rows; j++) {
                    temp[i][j] = boardTiles[i][j];
                }
            }
            int tempint = temp[x][y];
            temp[x][y] = temp[x + 1][y];
            temp[x + 1][y] = tempint;
            Board b = new Board(temp);
            iter.add(b);
        }
        if (y - 1 >= 0) {
            int[][] temp = new int[rows][rows];
            for (i = 0; i < rows; i++) {
                for (j = 0; j < rows; j++) {
                    temp[i][j] = boardTiles[i][j];
                }
            }
            int tempint = temp[x][y];
            temp[x][y] = temp[x][y - 1];
            temp[x][y - 1] = tempint;
            Board b = new Board(temp);
            iter.add(b);
        }
        if (y + 1 < rows) {
            int[][] temp = new int[rows][rows];
            for (i = 0; i < rows; i++) {
                for (j = 0; j < rows; j++) {
                    temp[i][j] = boardTiles[i][j];
                }
            }
            int tempint = temp[x][y];
            temp[x][y] = temp[x][y + 1];
            temp[x][y + 1] = tempint;
            Board b = new Board(temp);
            iter.add(b);
        }

        return iter;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] a = { { 1, 2 }, { 3, 4 } };
        Board b = new Board(a);
        System.out.println(b.toString());
    }

}