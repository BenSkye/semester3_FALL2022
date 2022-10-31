/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import util.Menu;
import util.Utils;

/**
 *
 * @author Administrator
 */
public final class Graph {

    int[][] adjacencyMatrixWithDistance;//adjcency matrix
    int[][] b;
    int[][] adjacencyMatrix;
    int[][] incidentMatrix;
    int n;//total vertex
    int[] parent = new int[5];
    Set<Edge> edges = new HashSet<>();
//    char[] vertex = "A B C D E".toCharArray();
    String[] vertex;
    final static int INF = 99999;

    public Graph() {

        loadFromFile("data_Graph.txt");
        n = adjacencyMatrixWithDistance.length;

        b = convertToINF(adjacencyMatrixWithDistance);
        adjacencyMatrix = toAdjencyMatrix(adjacencyMatrixWithDistance);
        getEdgeList();
    }

    public boolean loadFromFile(String name) {
        int count = 0;
        try {
            File f = new File(name);
            if (!f.exists()) {
                return false;
            }
            try ( FileReader fr = new FileReader(f)) {
                try ( BufferedReader br = new BufferedReader(fr)) {

                    vertex = toVertice(br.readLine());

                    adjacencyMatrixWithDistance = new int[vertex.length][vertex.length];
                    String info;
                    while ((info = br.readLine()) != null) {
                        adjacencyMatrixWithDistance[count++] = toInt(info);
                    }

                    br.close();
                }
                fr.close();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean saveToFile(String filename) {
        if (adjacencyMatrixWithDistance == null) {
            System.out.println("Empty list");
            return false;
        }
        try {
            File f = new File(filename);
            try ( FileWriter fw = new FileWriter(f);  PrintWriter pw = new PrintWriter(fw)) {
                for (int i = 0; i < vertex.length; i++) {
                    pw.print(vertex[i] + " ");
                }
                pw.println();

                for (int i = 0; i < adjacencyMatrixWithDistance.length; i++) {
                    for (int j = 0; j < adjacencyMatrixWithDistance.length; j++) {
                        pw.print(adjacencyMatrixWithDistance[i][j] + " ");

                    }
                    pw.println();
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return true;
    }

    private String[] toVertice(String name) {
        String name2[] = name.split(" ");
        return name2;
    }

    private int[] toInt(String name) {
        String name2[] = name.split(" ");
        int[] a = new int[name2.length];
        for (int i = 0; i < name2.length; i++) {
            a[i] = Integer.parseInt(name2[i]);
        }
        return a;
    }

    private void visit(int i) {
        System.out.print(vertex[i] + "  ");
    }

    //Breadth first traversal
    private void BFT(int u, boolean[] c) {
        MyQueue mq = new MyQueue();
        mq.enqueue(u);
        c[u] = true;
        while (!mq.isEmpty()) {
            int j = (int) mq.dequeue();
            visit(j);
            for (int i = 0; i < n; i++) {
                if (!c[i] && adjacencyMatrixWithDistance[j][i] > 0) {
                    mq.enqueue(i);
                    c[i] = true;
                }
            }
        }
    }

    //Breath first traversal
    public void BFT(int u) {
        boolean[] c = new boolean[n];
        BFT(u, c);
        for (int i = 0; i < n; i++) {
            if (!c[i]) {
                BFT(i, c);
            }

        }
        System.out.println("");
    }

    //Depth first traversal
    private void DFT(int i, boolean[] c) {
        c[i] = true;
        visit(i);
        for (int j = 0; j < n; j++) {
            if (!c[j] && adjacencyMatrixWithDistance[i][j] > 0) {
                DFT(j, c);
            }
        }
    }

    public void DFT(int i) {
        boolean[] c = new boolean[n];
        DFT(i, c);
        for (int j = 0; j < n; j++) {
            if (!c[j]) {
                DFT(j, c);
            }
        }
        System.out.println("");
    }

    public void printAdjacencyMatrix() {
        System.out.println("This is adjacency matrix");
        System.out.print("    ");
        for (int i = 0; i < vertex.length; i++) {
            System.out.print(vertex[i] + "   ");
        }
        System.out.println("");
        for (int i = 0; i < adjacencyMatrixWithDistance.length; i++) {
            System.out.print(vertex[i] + "   ");

            for (int j = 0; j < adjacencyMatrixWithDistance.length; j++) {
                System.out.print(adjacencyMatrixWithDistance[i][j] + "   ");
            }
            System.out.println("");

        }

    }

    private void getIncidentMatrix() {

        if (edges != null && vertex != null) {
            incidentMatrix = new int[vertex.length][edges.size()];
            int currentEdge;
            for (int i = 0; i < vertex.length; i++) {
                currentEdge = 0;
                for (Edge x : edges) {

                    if (x.getSource().equalsIgnoreCase(Vertice(i)) || x.getDestination().equalsIgnoreCase(Vertice(i))) {
                        incidentMatrix[i][currentEdge++] = x.getDistance();
                    } else {
                        incidentMatrix[i][currentEdge++] = 0;
                    }

                }
            }
        }
    }

    public void printIncidentMatrix() {
        if (edges != null && vertex != null) {
            getIncidentMatrix();
            System.out.println("This is incident matrix");
            System.out.print("  ");
            for (Edge x : edges) {
                System.out.print(x.getSource() + "" + x.getDestination() + " ");
            }
            System.out.println("");
            for (int i = 0; i < incidentMatrix.length; i++) {
                System.out.print(vertex[i] + " ");
                for (int j = 0; j < incidentMatrix[i].length; j++) {
                    System.out.print(" " + incidentMatrix[i][j] + " ");
                }
                System.out.println("");
            }
        } else {
            System.out.println("Data are failed in loading");
        }
    }

    private void getEdgeList() {
        if (edges != null && vertex != null) {
            for (int i = 0; i < adjacencyMatrixWithDistance.length; i++) {
                for (int j = i + 1; j < adjacencyMatrixWithDistance.length; j++) {
                    if (adjacencyMatrixWithDistance[i][j] != 0) {
                        edges.add(new Edge(Vertice((i > j) ? j : i), Vertice((i < j) ? j : i), adjacencyMatrixWithDistance[i][j]));
                    }
                }
            }
        }
    }

    public void printEdge() {
        getEdgeList();
        if (edges.isEmpty()) {
            System.out.println("empty");
        } else {
            for (Edge e : edges) {
                System.out.println(e.toString());;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> convertToAdjencyList() {
        ArrayList<ArrayList<Integer>> adjListArray = convert(adjacencyMatrixWithDistance);
        return adjListArray;
    }

    private ArrayList<ArrayList<Integer>> convert(int[][] a) {
        // no of vertices
        int l = a[0].length;
        ArrayList<ArrayList<Integer>> adjListArray
                = new ArrayList<ArrayList<Integer>>(l);

        // Create a new list for each
        // vertex such that adjacent
        // nodes can be stored
        for (int i = 0; i < l; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }

        int i, j;
        for (i = 0; i < a[0].length; i++) {
            for (j = 0; j < a.length; j++) {
                if (a[i][j] != 0) {
                    adjListArray.get(i).add(j);
                }
            }
        }

        return adjListArray;
    }

    // Function to print the adjacency list
    public void printAdjacencyList(ArrayList<ArrayList<Integer>> adjListArray) {
        // Print the adjacency list
        System.out.println("This is adjacency list");
        for (int v = 0; v < adjListArray.size(); v++) {
            System.out.print(Vertice(v) + ":");
            for (Integer u : adjListArray.get(v)) {
                System.out.print(" " + Vertice(u));
            }
            System.out.println();
        }
    }

    private void computeDegree(ArrayList<ArrayList<Integer>> adjListArray, int a) {
        int count = 0;
        for (Integer u : adjListArray.get(a)) {
            count++;
        }
        System.out.println("The degree of vertex is/are: " + count);
    }

    /*__________________________________________________________________________*/
    public void Dijkstra(int u, int v) {
        boolean[] c = new boolean[n];
        int[] s = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = u;
            d[i] = b[u][i];
        }
        int curr = u;
        while (curr != v) {

            int k = -1, min = INF;
            for (int i = 0; i < n; i++) {
                if (d[i] < min && !c[i]) {
                    min = d[i];
                    k = i;
                }
            }
            if (min == INF) {
                System.out.println("Shortest path from u to v not found");
                return;
            }
            curr = k;
            c[k] = true;

            for (int i = 0; i < n; i++) {
                if (d[i] > d[k] + b[k][i]) {
                    d[i] = d[k] + b[k][i];
                    s[i] = k;
                }
            }
        }

        System.out.println("Shortest path from u to v is " + d[v]);
        int[] h = new int[n];
        int hn = 0;
        h[hn] = v;
        int[] w = new int[n];
        int wn = 0;
        int x, y = v;

        while (u != v) {
            v = s[v];
            h[++hn] = v;
        }

        for (int i = hn; i >= 0; i--) {
            x = y;
            y = h[i];
            w[wn] = b[x][y];
            wn++;
        }

        int k = 1;
        System.out.print(vertex[h[hn]]);
        for (int i = hn - 1; i >= 0; i--) {
            System.out.print("->" + vertex[h[i]] + "(" + w[k++] + ")");
        }
        System.out.println("");
        System.out.println("Distance from starting point to other points");
        for (int i = hn - 1; i >= 0; i--) {
            System.out.println(vertex[h[hn]] + "->" + vertex[h[i]] + "(" + d[h[i]] + ")");
        }

    }

    /*Kruskal Alogrithm-------------------------------------------------- */
    private int find(int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    private void union1(int i, int j) {
        int a = find(i);
        int b = find(j);
        parent[a] = b;
    }

    private void kruskalMST(int cost[][]) {
        int mincost = 0;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int edge_count = 0;
        while (edge_count < n - 1) {
            int min = INF, a = -1, b = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (find(i) != find(j) && cost[i][j] < min) {
                        min = cost[i][j];
                        a = i;
                        b = j;
                    }
                }
            }

            union1(a, b);
            System.out.printf("Edge %d:(%s, %s) cost:%d \n",
                    edge_count++, Vertice(a), Vertice(b), min);
            mincost += min;
        }
        System.out.printf("\n Minimum cost= %d \n", mincost);
    }

    public void printKruskal() {

        kruskalMST(b);
    }

    private int[][] toAdjencyMatrix(int[][] b) {
        int a[][] = new int[n][n];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[i][j] != 0) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = 0;
                }
            }
        }
        return a;
    }

    private void findpath(int[][] graph, int n) {
        Vector<Integer> numofadj = new Vector<>();

        for (int i = 0; i < n; i++) {
            numofadj.add(accumulate(graph[i], 0));
        }

        int startPoint = 0, numofodd = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (numofadj.elementAt(i) % 2 == 1) {
                numofodd++;
                startPoint = i;
            }
        }

        if (numofodd > 2) {
            System.out.println("No Solution");
            return;
        }
        System.out.println("This is euler path");
        Stack<Integer> stack = new Stack<>();
        Vector<Integer> path = new Vector<>();
        int cur = startPoint;

        while (!stack.isEmpty() || accumulate(graph[cur], 0) != 0) {

            if (accumulate(graph[cur], 0) == 0) {
                path.add(cur);
                cur = stack.pop();

            } else {
                for (int i = 0; i < n; i++) {
                    if (graph[cur][i] == 1) {
                        stack.add(cur);
                        graph[cur][i] = 0;
                        graph[i][cur] = 0;
                        cur = i;
                        break;
                    }
                }
            }
        }

        // print the path
        for (int ele : path) {
            System.out.print(Vertice(ele) + " -> ");
        }
        System.out.println(Vertice(cur));
    }

    private int accumulate(int[] arr, int sum) {
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    public void printEulerPath() {
        findpath(adjacencyMatrix, n);
    }

//----------------------------------------
    private int minKey(int key[], Boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < n; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    public void primMST() {
        primMST(adjacencyMatrixWithDistance);
    }

    private void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < n; i++) {
            System.out.println(Vertice(parent[i]) + " - " + Vertice(i) + "\t"
                    + graph[i][parent[i]]);
        }
    }

    private void primMST(int graph[][]) {
        int parent[] = new int[n];
        int key[] = new int[n];
        Boolean mstSet[] = new Boolean[n];

        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < n - 1; count++) {

            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && mstSet[v] == false
                        && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    private void floydWarshall(int graph[][]) {
        int dist[][] = new int[n][n];
        int i, j, k;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j]
                            < dist[i][j]) {
                        dist[i][j]
                                = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        printSolution(dist);
    }

    private int[][] convertToINF(int a[][]) {
        int b[][] = new int[n][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == j) {
                    b[i][j] = 0;
                } else if (a[i][j] == 0) {
                    b[i][j] = INF;
                } else {
                    b[i][j] = a[i][j];
                }
            }
        }
        return b;
    }

    public void printSolution(int dist[][]) {
        System.out.println(
                "The following matrix shows the shortest "
                + "distances between every pair of vertices");
        for (int i = 0; i < n; ++i) {
            System.out.print("   " + vertex[i] + "");
        }
        System.out.println("");
        for (int i = 0; i < n; ++i) {
            System.out.print(vertex[i] + "  ");

            for (int j = 0; j < n; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    public void printFloyD() {
        floydWarshall(convertToINF(adjacencyMatrixWithDistance));
    }

    public String Vertice(int c) {
        return vertex[c];
    }

    public int index(String b) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].equalsIgnoreCase(b)) {
                return i;
            }
        }
        return -1;
    }

    public void printAndTraversal() {
        int choice;
        Menu menu = new Menu("Print and Traversals");
        menu.addNewOption("1-Print Adjency Matrix");
        menu.addNewOption("2-Print Incident Matrix");
        menu.addNewOption("3-Print Adjency List");
        menu.addNewOption("4-Traversal follows BFS");
        menu.addNewOption("5-Traversal follows DFS");
        menu.addNewOption("6-Exit");
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    printAdjacencyMatrix();
                    break;
                case 2:
                    printIncidentMatrix();
                    break;
                case 3:
                    printAdjacencyList(convertToAdjencyList());
                    break;
                case 4:
                    try {
                    String source = Utils.getString("Input the start (Only character): ", "The start is required");
                    BFT(index(source));
                } catch (Exception e) {
                    System.out.println(e);
                }

                break;
                case 5:
                    try {
                    String source1 = Utils.getString("Input the start (Only character): ", "The start is required");
                    DFT(index(source1));
                } catch (Exception e) {
                    System.out.println(e);
                }

                break;
                case 6:
                    break;

            }
        } while (choice != 6);
    }

    public void executeAlogrithm() {
        Menu menu = new Menu("Alogrithm");
        int choice;
        menu.addNewOption("1-Dijkstra Alogrithm");
        menu.addNewOption("2-FloyD Alogrithm");
        menu.addNewOption("3-Prim Alogrithm");
        menu.addNewOption("4-Euler Alogrithm");
        menu.addNewOption("5-Kruskal Alogrithm");
        menu.addNewOption("6-Exit");
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    try {
                    String source2 = Utils.getString("Input the position source (Only character):", "The position source is required");
                    String dest = Utils.getString("Input the position destination (Only character):", "The position destination is required");

                    Dijkstra(index(source2), index(dest));
                } catch (Exception e) {
                        System.out.println(e);
                }

                break;
                case 2:
                    printFloyD();
                    break;
                case 3:
                    primMST();
                    break;
                case 4:
                    printEulerPath();
                    break;
                case 5:
                    printKruskal();
                    break;
                case 6:
                    break;
            }
        } while (choice != 6);
    }

    public void caculateDegree() {
        String v = Utils.getString("Input degree: ", "Degree is required!");
        computeDegree(convertToAdjencyList(), index(v));
    }
}
