// Usage: java -ea TestDoubleMatrix
public class TestDoubleMatrix {
  public static void main(String[] args) {

    if(false)
    { // 引数の配列が不正な場合を確認
      DoubleMatrix a = new DoubleMatrix(new double[][]{{0}, {1, 2}});

      // 以下のメッセージが出力された後，プログラムが終了する
      // - - - - - - - - - - - - - - - - -
      // [エラー] 行列として解釈できません
      // [[0.0], [1.0, 2.0]]
      // - - - - - - - - - - - - - - - - -

    } // end of block


    { // rows, columns, sizeが正しく設定されているかを確認
      DoubleMatrix a = new DoubleMatrix(new double[][]{{0, 0, 0}});
      assert a.rows == 1;
      assert a.columns == 3;
      assert a.size == 3;

      DoubleMatrix b = new DoubleMatrix(
        new double[][] {
          {0},
          {0},
          {0},
          {0},
        }
      );
      assert b.rows == 4;
      assert b.columns == 1;
      assert b.size == 4;

      DoubleMatrix c = new DoubleMatrix(
        new double[][] {
          {0, 1, 5},
          {0, 2, 4},
          {0, 3, 3},
          {0, 4, 2},
          {0, 5, 1},
        }
      );
      assert c.rows == 5;
      assert c.columns == 3;
      assert c.size == 15;

    } // end of block


    { // get(), set()の動作確認
      DoubleMatrix a = new DoubleMatrix(
        new double[][] {
          {0, 1, 2},
          {3, 4, 5},
          {6, 7, 8},
        }
      );

      assert a.get(1, 1) == 4;
      assert a.get(2, 2) == 8;

      a.set(1, 1, -90);
      a.set(2, 2, 256);
      assert a.get(1, 1) == -90;
      assert a.get(2, 2) == 256;

    } // end of block


    { // 行列の元となった配列の値の変更の影響を受けないことを確認
      double[][] val = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
      };
      DoubleMatrix a = new DoubleMatrix(val);

      for(int i = 0; i < val.length; i++) {
        for(int j = 0; j < val[i].length; j++) {
          val[i][j] = 42;
        }
      }

      for(int i = 0; i < val.length; i++) {
        for(int j = 0; j < val[i].length; j++) {
          assert a.get(i, j) == a.rows * i + j;
        }
      }

    } // end of block


    { // isEqual() の動作確認
      DoubleMatrix a = new DoubleMatrix(
        new double[][] {
          {0,  1,  2},
          {3,  4,  5},
          {6,  7,  8},
          {9, 10, 11},
        }
      );

      DoubleMatrix b = new DoubleMatrix(
        new double[][] {
          {0,  1,  2},
          {3,  4,  5},
          {6,  7,  8},
        }
      );

      DoubleMatrix c = new DoubleMatrix(
        new double[][] {
          {0,  1},
          {3,  4},
          {6,  7},
          {9, 10},
        }
      );

      DoubleMatrix d = new DoubleMatrix(
        new double[][] {
          {0,  1,  2},
          {3,  4,  5},
          {6,  7,  8},
          {9, 10, 11},
        }
      );

      DoubleMatrix e = new DoubleMatrix(
        new double[][] {
          {0,  1,  2},
          {3,  4,  5},
          {6,  7,  8},
          {9, 10, -11},
        }
      );

      assert !a.isEqual(null);
      assert a.isEqual(a);

      // 行数が異なる場合
      assert !a.isEqual(b);
      assert !b.isEqual(a);

      // 列数が異なる場合
      assert !a.isEqual(c);
      assert !c.isEqual(a);

      // 同じ場合
      assert a.isEqual(d);
      assert d.isEqual(a);

      // 型は等しいが，成分の値が一部異っている場合
      assert !a.isEqual(e);
      assert !e.isEqual(a);

    } // end of block


    if(false)
    { // toString() の呼び出し
      DoubleMatrix a = new DoubleMatrix(new double[][]{{0,  1,  2}});
      System.out.println(a);

      DoubleMatrix b = new DoubleMatrix(
        new double[][] {
          {0},
          {1},
          {2},
        }
      );
      System.out.println(b);

      DoubleMatrix c = new DoubleMatrix(
        new double[][] {
          {0, 3, 6,  9},
          {1, 4, 7, 10},
          {2, 5, 8, 11},
        }
      );
      System.out.print(c);
    } // end of block


    { // add() の動作確認
      DoubleMatrix a = new DoubleMatrix(new double[][]{{1,  2,  3}});
      DoubleMatrix b = new DoubleMatrix(
        new double[][] {
          {1, 4, 5},
          {2, 5, 6},
          {3, 6, 7},
        }
      );
      DoubleMatrix c = new DoubleMatrix(
        new double[][] {
          {1, 4},
          {2, 5},
          {3, 6},
        }
      );

      DoubleMatrix d = new DoubleMatrix(
        new double[][] {
          {1, 4},
          {2, 5},
          {3, 6},
        }
      );

      DoubleMatrix e = new DoubleMatrix(
        new double[][] {
          {-1, 4},
          {-2, 5},
          {-3, 6},
        }
      );

      DoubleMatrix f = new DoubleMatrix(
        new double[][] {
          {0, 8},
          {0, 10},
          {0, 12},
        }
      );


      // 実引数がnullなら例外
      try {
        a.add(null);
      }
      catch(NullPointerException npe) {
        System.err.println("a.add(null) => " + npe);
      }

      // 行数が異なる場合
      assert a.add(b) == null;
      assert b.add(a) == null;

      // 列数が異なる場合
      assert b.add(c) == null;
      assert c.add(b) == null;

      // 加算結果が正しいかどうか
      assert d.add(e).isEqual(f);
      assert e.add(d).isEqual(f);

    } // end of block


    { // sub() の動作確認
      DoubleMatrix a = new DoubleMatrix(
        new double[][] {
          {1, 4, 7, 10},
          {2, 5, 8, 11},
          {3, 6, 9, 12},
        }
      );

      DoubleMatrix b = new DoubleMatrix(
        new double[][] {
          { 1,  1,  1,  1},
          {-1, -1, -1, -1},
          { 2,  2,  2,  2},
        }
      );

      DoubleMatrix c = new DoubleMatrix(
        new double[][] {
          {0, 3, 6,  9},
          {3, 6, 9, 12},
          {1, 4, 7, 10},
        }
      );

      assert a.sub(b).isEqual(c);

    } // end of block

    System.err.println();
    System.err.println("テスト完了");
  } // end of main()
} // end of class TestDoubleMatrix
