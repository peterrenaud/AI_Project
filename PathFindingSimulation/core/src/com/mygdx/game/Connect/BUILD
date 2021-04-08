java_library(
    name = "geocode",
    srcs = ["test_Geocode.java"],
    visibility = ["//src/User_Interface:__pkg__"],
)

java_library(
    name = "DBAccess",
    srcs = ["test_DB.java"],
    deps = [":Entries_and_Nodes", "//lib:mongodb","//lib:mongodb-sync", "//lib:bson","//lib:mongodb-streams", "//lib:jackson",],
    visibility = ["//src/User_Interface:__pkg__"],
)

java_library(
    name = "StreetFinder",
    srcs = ["StreetFinder.java"],
    deps = [":geocode", ":Entries_and_Nodes","//lib:mongodb","//lib:mongodb-sync", "//lib:bson","//lib:mongodb-streams", "//lib:jackson",],
    visibility = ["//src/User_Interface:__pkg__", "//src/Dijkstra:__pkg__"],
)

java_library(
    name = "Entries_and_Nodes",
    srcs = ["JunctionNode.java", "StreetNode.java",  "StreetEntry.java", "JunctionEntry.java", "ElementEntry.java", "JunctionPath.java"],
    visibility = ["//src/User_Interface:__pkg__", "//src/Dijkstra:__pkg__"],
)