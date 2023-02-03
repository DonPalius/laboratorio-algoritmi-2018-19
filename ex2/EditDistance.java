package ex2;

// 
class EditDistance {
	
	private static String rest(String s){
		if(s.length() > 1)
			return s.substring(1);
		else 
			return "";
	}
	
	static int edit_distance(String s1, String s2){
		
		if(s1.length() == 0) 
			return s2.length();
		
		if(s2.length() == 0) 
			return s1.length();
		
		int dnoop,dcanc,dins,dreplace;
		
		//dnoop 
		if(s1.charAt(0) ==  s2.charAt(0))
			return edit_distance(rest(s1), rest(s2));
		else 
			dnoop = Integer.MAX_VALUE;
		//dcanc
		dcanc = 1 + edit_distance(s1,rest(s2));
		//dins
		dins = 1 + edit_distance(rest(s1),s2);
		//dreplace
		dreplace = 1 + edit_distance(rest(s1),rest(s2));
		
		return Math.min(Math.min(Math.min(dnoop, dcanc), dreplace), dins);
	}
	
	
	
	static int edit_distance_dyn(String s1, String s2){
	// Initializing the matrix 
        int[][] editDistanceMatrice = new int[s1.length() + 1][s2.length() + 1];

        // Filling the matrix cells with -1 (except for last row and last column)
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                editDistanceMatrice[i][j] = -1;
            }
        }

		return editDistanceDyn(s1, s2, s1.length(), s2.length(), editDistanceMatrice);
		
	}	
		
		
	static int editDistanceDyn(String s1, String s2, int ls1, int ls2, int[][] m){
		// If |s1| = 0 then editDistance(s1,s2) = |s2|
        if (ls1 == 0) {
            return ls2;
        }

        // If |s2| = 0 then editDistance(s1,s2) = |s1|
        else if (ls2 == 0) {
            return ls1;
        }
		
		//If the recursive call has already been called , return its value memorized in the matrix
		else if (m[ls1 - 1][ls2 - 1] != -1) {
            return m[ls1 - 1][ls2 - 1];
        }
		
		else if (s1.charAt(ls1 - 1) == s2.charAt(ls2 - 1)) {
            return m[ls1 - 1][ls2 - 1] = editDistanceDyn(s1, s2, ls1 - 1, ls2 - 1, m);
        }

		 else {
            int dcanc = editDistanceDyn(s1, s2, ls1 - 1, ls2, m); // Remove
            int dins = editDistanceDyn(s1, s2, ls1, ls2 - 1, m); // Insert
            int dreplace = editDistanceDyn(s1, s2, ls1 -1, ls2 -1, m);// Replace
            return m[ls1 - 1][ls2 - 1] = 1 + Math.min(Math.min(dins, dcanc),dreplace);
        }
		
		
		
	}


}