add_library(tiger_runtime runtime.c)

target_compile_options(tiger_runtime PRIVATE -Wno-implicit-function-declaration)

add_executable(tiger_runtime_exe tigermain_stub.c)
target_link_libraries(tiger_runtime_exe tiger_runtime)
