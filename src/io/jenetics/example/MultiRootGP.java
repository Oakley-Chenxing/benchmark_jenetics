//package io.jenetics.example;
//
//import java.util.function.Function;
//
//import io.jenetics.Chromosome;
//import io.jenetics.Genotype;
//import io.jenetics.Mutator;
//import io.jenetics.engine.Codec;
//import io.jenetics.engine.Engine;
//import io.jenetics.engine.EvolutionResult;
//import io.jenetics.engine.Problem;
//import io.jenetics.util.ISeq;
//import io.jenetics.util.RandomRegistry;
//
//import io.jenetics.ext.SingleNodeCrossover;
//
//import io.jenetics.prog.ProgramChromosome;
//import io.jenetics.prog.ProgramGene;
//import io.jenetics.prog.op.EphemeralConst;
//import io.jenetics.prog.op.MathOp;
//import io.jenetics.prog.op.Op;
//import io.jenetics.prog.op.Var;
//
//public class MultiRootGP {
//	// Definition of the allowed operations.
//		static final ISeq<Op<Double>> OPERATIONS = ISeq.of(
//			MathOp.ADD,
//			MathOp.SUB,
//			MathOp.MUL
//		);
//
//		// Definition of the terminals.
//		static final ISeq<Op<Double>> TERMINALS = ISeq.of(
//			Var.of("x", 0),
//			EphemeralConst.of(() -> (double) RandomRegistry.random().nextInt(10))
//		);
//
//		static final Codec<ISeq<Function<Double[], Double>>, ProgramGene<Double>> CODEC =
//			Codec.of(
//				Genotype.of(
//					// First 'program'
//					ProgramChromosome.of(
//						5,
//						ch -> ch.root().size() <= 50,
//						OPERATIONS,
//						TERMINALS
//					)
//					,
//					// Second 'program'
//					ProgramChromosome.of(
//						5,
//						ch -> ch.root().size() <= 50,
//						OPERATIONS,
//						TERMINALS
//					)
//			),
//				gt -> gt.stream()
//					.map(Chromosome::gene)
//					.collect(ISeq.toISeq())
//			);
//
//		static double fitness(final ISeq<Function<Double[], Double>> programs) {
//			assert programs.size() == 2;
//			// Combine the two programs for the fitness function.
//			return 1;
//		}
//
//		public static void main(final String[] args) {
//			final Engine<ProgramGene<Double>, Double> engine = Engine
//				.builder(MultiRootGP::fitness, CODEC)
//				.minimizing()
//				.alterers(
//					new SingleNodeCrossover<>(),
//					new Mutator<>())
//				.build();
//
//			final Genotype<ProgramGene<Double>> gt = engine.stream()
//				.limit(3000)
//				.collect(EvolutionResult.toBestGenotype());
//
//			final ISeq<Function<Double[], Double>> programs = CODEC.decode(gt);
//		}
//}
