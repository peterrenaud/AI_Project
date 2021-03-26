java_binary(
	name = "test",
	srcs = ["test.java"],
	main_class = "src.User_Interface.test",
	deps = [":menu", "//src/Connect:DBAccess"],
)

java_library(
	name = "menu",
	srcs = ["test_Menu.java"],
	deps = ["//src/Connect:geocode", "//src/Connect:Entries", "//src/Connect:StreetFinder"],
)
