package io.jenetics.schaffer;

import io.jenetics.Mutator;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.Limits;
import io.jenetics.util.ISeq;
import io.jenetics.util.RandomRegistry;

import io.jenetics.ext.SingleNodeCrossover;
import io.jenetics.ext.util.TreeNode;

import io.jenetics.prog.ProgramGene;
import io.jenetics.prog.op.EphemeralConst;
import io.jenetics.prog.op.MathExpr;
import io.jenetics.prog.op.MathOp;
import io.jenetics.prog.op.Op;
import io.jenetics.prog.op.Var;
import io.jenetics.prog.regression.Error;
import io.jenetics.prog.regression.LossFunction;
import io.jenetics.prog.regression.Regression;
import io.jenetics.prog.regression.Sample;
public class Schaffer2 {
	// Definition of the allowed operations.
	private static final ISeq<Op<Double>> OPS =
		ISeq.of(MathOp.ADD, MathOp.SUB, MathOp.MUL);

	// Definition of the terminals.
	private static final ISeq<Op<Double>> TMS = ISeq.of(
		Var.of("x", 0),
		EphemeralConst.of(() -> (double)RandomRegistry.random().nextInt(10))
	);
	private static final Regression<Double> REGRESSION = Regression.of(
			Regression.codecOf(OPS, TMS, 2, t -> t.gene().size() < 30),
			Error.of(LossFunction::mse),
			// ? How to set the data
			Sample.ofDouble(-1.0, -8.0000),
			Sample.ofDouble(-0.9, -6.2460)
		);
	public static void main(final String[] args) {
		final Engine<ProgramGene<Double>, Double> engine = Engine
			.builder(REGRESSION)
			.minimizing()
			.alterers(
				new SingleNodeCrossover<>(0.1),
				new Mutator<>())
			.build();

		final EvolutionResult<ProgramGene<Double>, Double> result = engine.stream()
			.limit(Limits.byFitnessThreshold(0.01))
			.collect(EvolutionResult.toBestEvolutionResult());

		final ProgramGene<Double> program = result.bestPhenotype()
			.genotype()
			.gene();

		final TreeNode<Op<Double>> tree = program.toTreeNode();
		MathExpr.rewrite(tree);
		System.out.println("Generations: " + result.totalGenerations());
		System.out.println("Function:    " + new MathExpr(tree));
		System.out.println("Error:       " + REGRESSION.error(tree));
	}

	// The function we want to determine.
	private static double f(final double x) {
		 double sum = 0.0f;
		 double d = 0.50f;
		 return x*x;
		// double temp1 = (double) ((Math.sin(rep[i] * rep[i])-(rep[i+1] * rep[i+1]))*(Math.sin(rep[i] * rep[i])-(rep[i+1] * rep[i+1])));
		// double temp2 = (double) (1.0f + (0.001f*((rep[i] * rep[i])+(rep[i+1] * rep[i+1]))));
		//	   return sum += d + (temp1 - d) / (temp2 * temp2);
	}
}
