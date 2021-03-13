# LZW Compression Algorithm

The Lempel–Ziv–Welch (LZW) algorithm is a lossless data compression algorithm. It is an adaptive compression algorithm that does not assume prior knowledge of the input data distribution.. It is simple to implement and has the potential for very high throughput in hardware implementations.

The user will be asked to upload a file and as output compressed file will be downloaded. Compare file size for verification.


Tools Required:
~~~
Java : 1.8 and above
Postman : for API testing
IDE: VS code, STS, Eclipse
~~~

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

## About Algorithm:
HashMap data structure is used to implement the algorithm and it contains the ASCII characters as KEY along with its ASCII value as VALUE for encoding.

1. BufferedReader and BufferedWriter for reading and writing to files.
2. For encoding, Compressing.java file is used. It will generate compressed (LZW) file.
3. For decoding compressed file, Decompressing.java is used. It will generate decoded text file, whose contents will be same as the initial input file.
4. The compression file is created using charset UTF_16BE and stored in 16-bit format.

## Application of LZW
~~~
1. GIF and TIFF files
2. Adobe Acrobat Softwaare
~~~
