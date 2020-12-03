# SnpEffAnalyzer
Takes the output of SnpEff, filters user defined positions and provided it in a nice way.

**Usage:** 

java -jar SnpEffAnalyzer-1.0.jar -h

**Options:**

-h  
*Shows this help page.*

 -i <INPUT>          
  *The input file (= output of SNPEff).*
  
 -exclude <EXCLUDE>   
  *Positions to exclude (gff file).*
  
 -include <INCLUDE>  
  *Positions to consider (txt file, one position per line).*
