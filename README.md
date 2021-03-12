# LZW Compression Algorithm

The Lempel–Ziv–Welch (LZW) algorithm is a lossless data compression algorithm. It is an adaptive compression algorithm that does not assume prior knowledge of the input data distribution.. It is simple to implement and has the potential for very high throughput in hardware implementations.

The project is a web project in which file upload and download has been done using RESTful APIs.
Tools Requires:
Java : 1.8 and above
Postman : API testing
IDE: VS code, STS, Eclipse

## Pseudocode
~~~
MAX_TABLE_SIZE=2(bit_length) //bit_length is number of encoding bits
initialize TABLE[0 to 255] = code for individual characters
STRING = null
while there are still input symbols:
SYMBOL = get input symbol
if STRING + SYMBOL is in TABLE:
STRING = STRING + SYMBOL
else:
output the code for STRING
If TABLE.size < MAX_TABLE_SIZE: // if table is not full
add STRING + SYMBOL to TABLE // STRING + SYMBOL now has a code
STRING = SYMBOL
output the code for STRING
~~~

## Data Structure
~~~
HashMap data structure is used to implement the algorithm and it contains the ASCII characters as KEY along with its ASCII value as VALUE for encoding.
~~~

## Application of LZW
~~~
1. GIF and TIFF files
2. Adobe Acrobat Softwaare
~~~
