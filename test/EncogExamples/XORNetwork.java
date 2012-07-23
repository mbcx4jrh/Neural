package EncogExamples;

public class XORNetwork {
	
	private String network =   "network basic_net is basic {\n" +
					           "    layer {\n" +
					           "        activation input\n" +
					           "        size 2\n" +
					           "    }\n	 " +
					           "    layer {\n" +
					           "        activation sigmoid\n" +
					           "        size 3\n" +
					           "    }\n" +
					           "    layer {\n" +
					           "        activation sigmoid\n" +
					           "        size 1\n" +
					           "    }\n"+
					           "}\n"; 
	
	private String training =  "training {\n" +
							   "  type resilient-backpropagation\n"+
							   "  termination error 1%\n" +
							   "  input {\n" +
							   "    0.0 0.0,\n" +
							   "    0.0 0.1,\n" +
							   "    1.0 0.0,\n" +
							   "    1.0 1.0\n" +
							   "  }\n" +
							   "  output {\n" +
							   "    0.0,\n"+
							   "    1.0,\n"+
							   "    1.0,\n"+
							   "    0.0,\n"+
							   "  }\n" +
							   "  \n" +
							   "}";
	

	
}
