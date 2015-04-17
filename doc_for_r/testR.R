
library("rjson")



scriptStart <- function(data1)
{
    json_data <- fromJSON(data1)

    cat(json_data$arg1)
    cat(json_data$arg2)
    cat(json_data$a)
    cat(json_data$b)
    
    sum_a <- sum(json_data$a)
    
    myresult <- toJSON(sum_a)
    return (myresult)
}
