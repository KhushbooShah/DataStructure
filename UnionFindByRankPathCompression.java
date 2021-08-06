/**
 * Union Find with Rank & Path Compression when elements represented using int
 */
public class UnionFindByRankPathCompression {

    int[] parent;
    int[] sizeOfElement;

    public UnionFindByRankPathCompression(int size) {
        if(size <= 0)
            throw new IllegalArgumentException("Size cannot be less than or equal to zero");

        parent = new int[size];

        //Initialize parent array to itself (i.e self parent)
        for(int i=0; i < size; i++) {
            parent[i] = i;
            sizeOfElement[i] = i;
        }
    }

    public void union(int a, int b) {
        int rootOfA = find(a);
        int rootOfB = find(b);

        //We merge the smaller sized component into the larger one
        if(rootOfA != rootOfB) {
            if(sizeOfElement[rootOfA] < sizeOfElement[rootOfB]) {
                parent[rootOfA] = b;
                sizeOfElement[b] += sizeOfElement[a];
            }
            else {
                parent[rootOfB] = a;
                sizeOfElement[a] += sizeOfElement[b];
            }
        }
    }

    //Path compression happens during the find operation
    public int find(int a) {
        if(a != parent[a]){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    public static void main(String[] args) {
        int noOfElements = 9;
        UnionFindWithPathCompression uf = new UnionFindWithPathCompression(noOfElements);
        uf.union(0,1);
        uf.union(2,3);
        uf.union(3,7);
        uf.union(5,6);
        uf.union(4,8);
        uf.union(4,5);
        
        for(int i=0; i < noOfElements; i++)
            System.out.println("Element "+ i + "'s root is " +uf.find(i));   
            
        System.out.println("0 and 7 connected : " +uf.isConnected(0, 7));
        System.out.println("4 and 6 connected : " +uf.isConnected(4, 6));
    }
    
}
