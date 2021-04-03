java_binary(
	name = "test_dijkstra",
	srcs = ["TestDijkstra.java"],
	main_class = "TestDijkstra",
	deps = [":dijkstra", ":node_and_path"],
)

java_library(
	name = "dijkstra",
	srcs = ["Dijkstra.java"],
	deps = [":node_and_path", "//src/Connect:Entries_and_Nodes", "//src/Connect:StreetFinder"],
	visibility = ["//src/User_Interface:__pkg__"],
)

java_library(
	name = "node_and_path",
	srcs = ["Path.java","Node.java", "Graph.java"],
)