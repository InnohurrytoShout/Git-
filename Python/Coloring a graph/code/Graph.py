# Do not change the name of this file or the class name.
class Graph:

    def __init__(self, file_path):
        self.graph_repr = self.store_graph(file_path)
        self.n_nodes = len(self.graph_repr)

    def store_graph(self, file_path):
        """Store graph as an adjacency matrix or adjacency list.

        Arguments:
            file_path (str): path of file storing graph in the following format:
            The first line contains the number of vertices (|V |). The vertices
            are numbered 1 through |V |. Each subsequent line contains a pair of
            vertices such that each such pair defines an edge.

        Returns:
            graph_repr (list of list of int): an adjacency matrix or adjacency
            list storing graph
        """
        with open(file_path) as handle:
            lines = list(w.strip() for w in handle)
        n = int(lines[0])
        lines.pop(0)
        adj = [[] for i in range(n + 1)]
        "iterate through the file_path"
        i = 0
        while i < len(lines):
            exp = lines[i].split()
            u = int(exp[0])
            l = int(exp[1])
            adj[u].append(l)
            adj[l].append(u)
            i += 1
        adj.pop(0)
        # print(adj)
        return adj

    def check_2colorable(self):
        """Determine whether or not the graph is 2-colorable and return colors.

        Returns:
            colors (list of int): a list containing colors of the nodes if
            graph is 2-colorable. The color of each node needs to be one of the
            values 0 or 1. We are not asking for all different possible
            colorings of the graph and the coloring is fine as long as
            nodes of the same color don't share an edge. If graph is not
            2-colorable, return [].
        """
        adj, n = self.graph_repr, self.n_nodes
        color = [-1] * (n + 1)
        circle = []
        c = []
        b = []
        par = [0] * (n + 1)
        queue = []
        visited = [False] * n
        "the outer loop to solve the isolated nodes"
        for i in range(0, n):
            if not visited[i]:
                queue.append(i + 1)
                visited[i] = True
            "BFS"
            while queue:
                s = queue.pop(0)
                "Color the first node"
                if color[s] == -1:
                    color[s] = 1
                "Color all the child nodes of current node"
                for each in adj[s - 1]:
                    "when not colored"
                    if color[each] == -1:
                        color[each] = 1 - color[s]
                    "when not colorable"
                    if color[each] == color[s]:
                        while par[each] != 0 and par[s] != 0:
                            b.append(each)
                            c.insert(0, s)
                            each = par[each]
                            s = par[s]
                            if par[each] == par[s]:
                                b.append(each)
                                c.insert(0, s)
                                b.append(par[each])
                                circle = b + c
                                break
                        print(circle)
                        return False, circle
                "BFS continued"
                for nb in adj[s - 1]:
                    if not visited[nb - 1]:
                        visited[nb - 1] = True
                        queue.append(nb)
                        par[nb] = s

        color.pop(0)
        # print(color)
        return True, color


def main():
    # Main function, you can edit as needed
    # Extra import may also be made locally in this function

    graph = Graph("data/largegraph2")
    colors = graph.check_2colorable


if __name__ == '__main__':
    main()
