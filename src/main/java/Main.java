import java.io.File;
import java.io.PrintWriter;
import java.util.Set;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        Communicator communicator = new Communicator();
        CommandLineParser commandLineParser = new CommandLineParser(communicator);
        commandLineParser.parse(args);
        communicator = commandLineParser.getCommunicator();
        if (communicator.getFile_exclude() != null && communicator.getFile_include() != null) {
            System.err.println("Both files (positions to exclude and include) are provided. Please specify only either or.");
        } else {
            String snpeff_file;
            String positions_file;
            String result_file;
            Set positions_to_include;
            SnpEffParser parser;
            String result;
            PrintWriter out;
            Throwable var11;
            if (communicator.getFile_exclude() != null) {
                System.out.println("GFF file with positions to exclude is provided.");
                snpeff_file = communicator.getInput();
                System.out.println("Input file: " + snpeff_file);
                positions_file = communicator.getFile_exclude();
                System.out.println("File with positions to exclude: " + positions_file);
                result_file = snpeff_file.substring(0, snpeff_file.lastIndexOf(File.separator)) + File.separator + "SnpEffTable.tsv";
                System.out.println("Results will be saved in: " + result_file);
                ReadGFF readGFF = new ReadGFF();
                System.out.print("Reading gff file.....");
                positions_to_include = readGFF.getPositionsToExclude(positions_file);
                System.out.println("Positions to exclude: " + positions_to_include.size());
                System.out.print("Finished\n");
                parser = new SnpEffParser();
                System.out.print("Parsing SNPEff out file.....");
                result = parser.parse(snpeff_file, positions_to_include, (Set)null);
                System.out.print("Finished\n");

                try {
                    out = new PrintWriter(result_file);
                    var11 = null;

                    try {
                        out.println(result);
                        System.out.println("Run completed successfully");
                    } catch (Throwable var66) {
                        var11 = var66;
                        throw var66;
                    } finally {
                        if (out != null) {
                            if (var11 != null) {
                                try {
                                    out.close();
                                } catch (Throwable var63) {
                                    var11.addSuppressed(var63);
                                }
                            } else {
                                out.close();
                            }
                        }

                    }
                } catch (Exception var72) {
                    System.err.println("Something went wrong:\n" + var72);
                }
            } else if (communicator.getFile_include() != null) {
                System.out.println("List with positions 'only consider these positions' is provided.");
                snpeff_file = communicator.getInput();
                System.out.println("Input file: " + snpeff_file);
                positions_file = communicator.getFile_include();
                System.out.println("File with positions to include: " + positions_file);
                result_file = snpeff_file.substring(0, snpeff_file.lastIndexOf(File.separator)) + File.separator + "SnpEffTable.tsv";
                System.out.println("Results will be saved in: " + result_file);
                ReadTextFile readTxt = new ReadTextFile();
                System.out.print("Reading text file.....");
                positions_to_include = readTxt.getPositionsToInclude(positions_file);
                System.out.println("Positions to include: " + positions_to_include.size());
                System.out.print("Finished\n");
                parser = new SnpEffParser();
                System.out.print("Parsing SNPEff out file.....");
                result = parser.parse(snpeff_file, (Set)null, positions_to_include);
                System.out.print("Finished\n");

                try {
                    out = new PrintWriter(result_file);
                    var11 = null;

                    try {
                        out.println(result);
                        System.out.println("Run completed successfully");
                    } catch (Throwable var65) {
                        var11 = var65;
                        throw var65;
                    } finally {
                        if (out != null) {
                            if (var11 != null) {
                                try {
                                    out.close();
                                } catch (Throwable var62) {
                                    var11.addSuppressed(var62);
                                }
                            } else {
                                out.close();
                            }
                        }

                    }
                } catch (Exception var70) {
                    System.err.println("Something went wrong:\n" + var70);
                }
            } else {
                System.out.println("All positions will be used.");
                snpeff_file = communicator.getInput();
                System.out.println("Input file: " + snpeff_file);
                result_file = snpeff_file.substring(0, snpeff_file.lastIndexOf(File.separator)) + File.separator + "SnpEffTable.tsv";
                System.out.println("Results will be saved in: " + result_file);
                parser = new SnpEffParser();
                System.out.print("Parsing SNPEff out file.....");
                result = parser.parse(snpeff_file, (Set)null, (Set)null);
                System.out.print("Finished\n");

                try {
                    out = new PrintWriter(result_file);
                    Throwable var77 = null;

                    try {
                        out.println(result);
                        System.out.println("Run completed successfully");
                    } catch (Throwable var64) {
                        var77 = var64;
                        throw var64;
                    } finally {
                        if (out != null) {
                            if (var77 != null) {
                                try {
                                    out.close();
                                } catch (Throwable var61) {
                                    var77.addSuppressed(var61);
                                }
                            } else {
                                out.close();
                            }
                        }

                    }
                } catch (Exception var68) {
                    System.err.println("Something went wrong:\n" + var68);
                }
            }
        }

    }
}
