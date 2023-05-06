public class maximumBeer {
    
    /**
     * Trouve le nombre maximum de bières que Frank peut boire sans dépasser la limite K.
     * 
     * @param N         nombre de lignes dans la grille
     * @param M         nombre de colonnes dans la grille
     * @param K         limite de bières à ne pas dépasser
     * @param grille    grille de bières
     * @return          le nombre maximum de bières que Frank peut boire sans dépasser la limite K, ou -1 s'il n'y a pas de solution possible.
     * 
     * @requires N > 0 && M > 0 && K >= 0 && grille.length == N && (\forall int i; i >= 0 && i < N; grille[i].length == M)
     * @ensures (\result >= -1 && \result <= K) || \result == -1
     */

    public static int findMaxBeers(int N, int M, int K, int[][] grille) {
        int maxBeers = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int beers = findMaxBeersHelper(i, j, N, M, K, grille);
                if (beers > maxBeers) {
                    maxBeers = beers;
                }
            }
        }
        if (maxBeers <= K) {
            return maxBeers;
        } else {
            return -1;
        }
    }

    private static int findMaxBeersHelper(int i, int j, int N, int M, int K, int[][] grille) {
        if (i >= N || j >= M) {
            return 0;
        }
        int beers = grille[i][j];
        int rightBeers = findMaxBeersHelper(i, j+1, N, M, K, grille);
        int downBeers = findMaxBeersHelper (i+1, j, N, M, K, grille);
        int diagBeers = findMaxBeersHelper (i+1, j+1, N, M, K, grille);
        beers += Math.max(Math.max(rightBeers, downBeers), diagBeers);
        if (beers > K) {
            return 0;
        } else {
            return beers;
        }
    }
}