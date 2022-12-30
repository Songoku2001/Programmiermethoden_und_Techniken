#include <stddef.h>
#include <stdlib.h>

#define abortWithError(ex){printf("File: %s Line %d: %s\n", __FILE__,__LINE__,ex),exit(EXIT_FAILURE);}